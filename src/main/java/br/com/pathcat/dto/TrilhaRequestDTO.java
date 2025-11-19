package br.com.pathcat.dto;

import br.com.pathcat.domain.NivelTrilha;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrilhaRequestDTO {
    
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    private String descricao;
    
    @NotNull(message = "Nível é obrigatório")
    private NivelTrilha nivel;
    
    @Min(value = 1, message = "Carga horária deve ser no mínimo 1 hora")
    @NotNull(message = "Carga horária é obrigatória")
    private Integer cargaHoraria;
    
    private String focoPrincipal;
    
    private Set<Long> competenciasIds;
}
