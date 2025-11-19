package br.com.pathcat.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "matriculas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matricula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "trilha_id", nullable = false)
    private Trilha trilha;
    
    @Column(name = "data_inscricao")
    private LocalDateTime dataInscricao;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusMatricula status;
    
    @PrePersist
    protected void onCreate() {
        dataInscricao = LocalDateTime.now();
        if (status == null) {
            status = StatusMatricula.ATIVA;
        }
    }
}
