package br.com.pathcat.service;

import br.com.pathcat.domain.Competencia;
import br.com.pathcat.domain.Trilha;
import br.com.pathcat.dto.CompetenciaDTO;
import br.com.pathcat.dto.TrilhaRequestDTO;
import br.com.pathcat.dto.TrilhaResponseDTO;
import br.com.pathcat.exception.CompetenciaNaoEncontradaException;
import br.com.pathcat.exception.TrilhaNaoEncontradaException;
import br.com.pathcat.repository.CompetenciaRepository;
import br.com.pathcat.repository.TrilhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrilhaService {
    
    private final TrilhaRepository trilhaRepository;
    private final CompetenciaRepository competenciaRepository;
    
    @Transactional
    public TrilhaResponseDTO criar(TrilhaRequestDTO requestDTO) {
        Trilha trilha = new Trilha();
        trilha.setNome(requestDTO.getNome());
        trilha.setDescricao(requestDTO.getDescricao());
        trilha.setNivel(requestDTO.getNivel());
        trilha.setCargaHoraria(requestDTO.getCargaHoraria());
        trilha.setFocoPrincipal(requestDTO.getFocoPrincipal());
        
        if (requestDTO.getCompetenciasIds() != null && !requestDTO.getCompetenciasIds().isEmpty()) {
            Set<Competencia> competencias = new HashSet<>();
            for (Long competenciaId : requestDTO.getCompetenciasIds()) {
                Competencia competencia = competenciaRepository.findById(competenciaId)
                        .orElseThrow(() -> new CompetenciaNaoEncontradaException(competenciaId));
                competencias.add(competencia);
            }
            trilha.setCompetencias(competencias);
        }
        
        Trilha salva = trilhaRepository.save(trilha);
        return toResponseDTO(salva);
    }
    
    @Transactional(readOnly = true)
    public List<TrilhaResponseDTO> listarTodas() {
        return trilhaRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public TrilhaResponseDTO buscarPorId(Long id) {
        Trilha trilha = trilhaRepository.findById(id)
                .orElseThrow(() -> new TrilhaNaoEncontradaException(id));
        return toResponseDTO(trilha);
    }
    
    @Transactional
    public TrilhaResponseDTO atualizar(Long id, TrilhaRequestDTO requestDTO) {
        Trilha trilha = trilhaRepository.findById(id)
                .orElseThrow(() -> new TrilhaNaoEncontradaException(id));
        
        trilha.setNome(requestDTO.getNome());
        trilha.setDescricao(requestDTO.getDescricao());
        trilha.setNivel(requestDTO.getNivel());
        trilha.setCargaHoraria(requestDTO.getCargaHoraria());
        trilha.setFocoPrincipal(requestDTO.getFocoPrincipal());
        
        if (requestDTO.getCompetenciasIds() != null) {
            Set<Competencia> competencias = new HashSet<>();
            for (Long competenciaId : requestDTO.getCompetenciasIds()) {
                Competencia competencia = competenciaRepository.findById(competenciaId)
                        .orElseThrow(() -> new CompetenciaNaoEncontradaException(competenciaId));
                competencias.add(competencia);
            }
            trilha.setCompetencias(competencias);
        }
        
        Trilha atualizada = trilhaRepository.save(trilha);
        return toResponseDTO(atualizada);
    }
    
    @Transactional
    public void deletar(Long id) {
        if (!trilhaRepository.existsById(id)) {
            throw new TrilhaNaoEncontradaException(id);
        }
        trilhaRepository.deleteById(id);
    }
    
    private TrilhaResponseDTO toResponseDTO(Trilha trilha) {
        Set<CompetenciaDTO> competenciasDTO = trilha.getCompetencias().stream()
                .map(c -> new CompetenciaDTO(c.getId(), c.getNome(), c.getCategoria(), c.getDescricao()))
                .collect(Collectors.toSet());
        
        return new TrilhaResponseDTO(
                trilha.getId(),
                trilha.getNome(),
                trilha.getDescricao(),
                trilha.getNivel(),
                trilha.getCargaHoraria(),
                trilha.getFocoPrincipal(),
                competenciasDTO
        );
    }
}
