package br.com.pathcat.service;

import br.com.pathcat.domain.Matricula;
import br.com.pathcat.domain.Trilha;
import br.com.pathcat.domain.Usuario;
import br.com.pathcat.dto.MatriculaRequestDTO;
import br.com.pathcat.dto.MatriculaResponseDTO;
import br.com.pathcat.dto.TrilhaResponseDTO;
import br.com.pathcat.dto.UsuarioResponseDTO;
import br.com.pathcat.exception.TrilhaNaoEncontradaException;
import br.com.pathcat.exception.UsuarioNaoEncontradoException;
import br.com.pathcat.repository.MatriculaRepository;
import br.com.pathcat.repository.TrilhaRepository;
import br.com.pathcat.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatriculaService {
    
    private final MatriculaRepository matriculaRepository;
    private final UsuarioRepository usuarioRepository;
    private final TrilhaRepository trilhaRepository;
    private final TrilhaService trilhaService;
    private final UsuarioService usuarioService;
    
    @Transactional
    public MatriculaResponseDTO criar(MatriculaRequestDTO requestDTO) {
        Usuario usuario = usuarioRepository.findById(requestDTO.getUsuarioId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(requestDTO.getUsuarioId()));
        
        Trilha trilha = trilhaRepository.findById(requestDTO.getTrilhaId())
                .orElseThrow(() -> new TrilhaNaoEncontradaException(requestDTO.getTrilhaId()));
        
        Matricula matricula = new Matricula();
        matricula.setUsuario(usuario);
        matricula.setTrilha(trilha);
        
        Matricula salva = matriculaRepository.save(matricula);
        return toResponseDTO(salva);
    }
    
    @Transactional(readOnly = true)
    public List<TrilhaResponseDTO> listarTrilhasPorUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new UsuarioNaoEncontradoException(usuarioId);
        }
        
        List<Matricula> matriculas = matriculaRepository.findByUsuarioId(usuarioId);
        return matriculas.stream()
                .map(m -> trilhaService.buscarPorId(m.getTrilha().getId()))
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarUsuariosPorTrilha(Long trilhaId) {
        if (!trilhaRepository.existsById(trilhaId)) {
            throw new TrilhaNaoEncontradaException(trilhaId);
        }
        
        List<Matricula> matriculas = matriculaRepository.findByTrilhaId(trilhaId);
        return matriculas.stream()
                .map(m -> usuarioService.buscarPorId(m.getUsuario().getId()))
                .collect(Collectors.toList());
    }
    
    private MatriculaResponseDTO toResponseDTO(Matricula matricula) {
        return new MatriculaResponseDTO(
                matricula.getId(),
                matricula.getUsuario().getId(),
                matricula.getUsuario().getNome(),
                matricula.getTrilha().getId(),
                matricula.getTrilha().getNome(),
                matricula.getDataInscricao(),
                matricula.getStatus()
        );
    }
}
