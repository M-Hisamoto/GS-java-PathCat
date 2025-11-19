package br.com.pathcat.dto;

import br.com.pathcat.domain.NivelCarreira;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
    
    private Long id;
    private String nome;
    private String email;
    private String areaAtuacao;
    private NivelCarreira nivelCarreira;
    private LocalDateTime dataCadastro;
}
