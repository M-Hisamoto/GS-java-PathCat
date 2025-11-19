package br.com.pathcat.dto;

import br.com.pathcat.domain.NivelTrilha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrilhaResponseDTO {
    
    private Long id;
    private String nome;
    private String descricao;
    private NivelTrilha nivel;
    private Integer cargaHoraria;
    private String focoPrincipal;
    private Set<CompetenciaDTO> competencias;
}
