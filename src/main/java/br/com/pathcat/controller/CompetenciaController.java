package br.com.pathcat.controller;

import br.com.pathcat.dto.CompetenciaDTO;
import br.com.pathcat.service.CompetenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competencias")
@RequiredArgsConstructor
public class CompetenciaController {
    
    private final CompetenciaService competenciaService;
    
    @PostMapping
    public ResponseEntity<CompetenciaDTO> criar(@Valid @RequestBody CompetenciaDTO dto) {
        CompetenciaDTO response = competenciaService.criar(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<CompetenciaDTO>> listarTodas() {
        List<CompetenciaDTO> competencias = competenciaService.listarTodas();
        return ResponseEntity.ok(competencias);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CompetenciaDTO> buscarPorId(@PathVariable Long id) {
        CompetenciaDTO competencia = competenciaService.buscarPorId(id);
        return ResponseEntity.ok(competencia);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CompetenciaDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody CompetenciaDTO dto) {
        CompetenciaDTO response = competenciaService.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        competenciaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
