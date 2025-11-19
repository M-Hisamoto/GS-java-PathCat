package br.com.pathcat.service;

import br.com.pathcat.domain.Competencia;
import br.com.pathcat.dto.CompetenciaDTO;
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
    
    @Transactional(readOnly = true)
    public List<CompetenciaDTO> listarTodas() {
        return competenciaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
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
