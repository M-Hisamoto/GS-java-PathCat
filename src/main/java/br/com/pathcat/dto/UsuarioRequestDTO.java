package br.com.pathcat.dto;

import br.com.pathcat.domain.NivelCarreira;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {
    
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email é obrigatório")
    private String email;
    
    private String areaAtuacao;
    
    private NivelCarreira nivelCarreira;
}
