package br.com.pathcat.dto;

import br.com.pathcat.domain.StatusMatricula;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaResponseDTO {
    
    private Long id;
    private Long usuarioId;
    private String usuarioNome;
    private Long trilhaId;
    private String trilhaNome;
    private LocalDateTime dataInscricao;
    private StatusMatricula status;
}
