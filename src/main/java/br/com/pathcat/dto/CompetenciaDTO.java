package br.com.pathcat.dto;

import br.com.pathcat.domain.CategoriaCompetencia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetenciaDTO {
    
    private Long id;
    private String nome;
    private CategoriaCompetencia categoria;
    private String descricao;
}
