package br.com.pathcat.repository;

import br.com.pathcat.domain.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    
    List<Matricula> findByUsuarioId(Long usuarioId);
    
    List<Matricula> findByTrilhaId(Long trilhaId);
}
