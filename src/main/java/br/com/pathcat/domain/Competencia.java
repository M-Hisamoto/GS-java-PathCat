package br.com.pathcat.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "competencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Competencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaCompetencia categoria;
    
    @Column(length = 500)
    private String descricao;
}
