package br.com.pathcat.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaRequestDTO {
    
    @NotNull(message = "ID do usuário é obrigatório")
    private Long usuarioId;
    
    @NotNull(message = "ID da trilha é obrigatório")
    private Long trilhaId;
}
