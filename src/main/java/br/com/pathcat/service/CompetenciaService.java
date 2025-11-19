package br.com.pathcat.service;

import br.com.pathcat.domain.Competencia;
import br.com.pathcat.dto.CompetenciaDTO;
import br.com.pathcat.exception.CompetenciaNaoEncontradaException;
import br.com.pathcat.repository.CompetenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetenciaService {
    
    private final CompetenciaRepository competenciaRepository;
    
    @Transactional
    public CompetenciaDTO criar(CompetenciaDTO dto) {
        Competencia competencia = new Competencia();
        competencia.setNome(dto.getNome());
        competencia.setCategoria(dto.getCategoria());
        competencia.setDescricao(dto.getDescricao());
        
        Competencia salva = competenciaRepository.save(competencia);
        return toDTO(salva);
    }
    
    @Transactional(readOnly = true)
    public List<CompetenciaDTO> listarTodas() {
        return competenciaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public CompetenciaDTO buscarPorId(Long id) {
        Competencia competencia = competenciaRepository.findById(id)
                .orElseThrow(() -> new CompetenciaNaoEncontradaException(id));
        return toDTO(competencia);
    }
    
    @Transactional
    public CompetenciaDTO atualizar(Long id, CompetenciaDTO dto) {
        Competencia competencia = competenciaRepository.findById(id)
                .orElseThrow(() -> new CompetenciaNaoEncontradaException(id));
        
        competencia.setNome(dto.getNome());
        competencia.setCategoria(dto.getCategoria());
        competencia.setDescricao(dto.getDescricao());
        
        Competencia atualizada = competenciaRepository.save(competencia);
        return toDTO(atualizada);
    }
    
    @Transactional
    public void deletar(Long id) {
        if (!competenciaRepository.existsById(id)) {
            throw new CompetenciaNaoEncontradaException(id);
        }
        competenciaRepository.deleteById(id);
    }
    
    private CompetenciaDTO toDTO(Competencia competencia) {
        return new CompetenciaDTO(
                competencia.getId(),
                competencia.getNome(),
                competencia.getCategoria(),
                competencia.getDescricao()
        );
    }
}
