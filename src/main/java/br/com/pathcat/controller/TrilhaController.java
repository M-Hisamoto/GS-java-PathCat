package br.com.pathcat.controller;

import br.com.pathcat.dto.TrilhaRequestDTO;
import br.com.pathcat.dto.TrilhaResponseDTO;
import br.com.pathcat.service.TrilhaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trilhas")
@RequiredArgsConstructor
public class TrilhaController {
    
    private final TrilhaService trilhaService;
    
    @PostMapping
    public ResponseEntity<TrilhaResponseDTO> criar(@Valid @RequestBody TrilhaRequestDTO requestDTO) {
        TrilhaResponseDTO response = trilhaService.criar(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<TrilhaResponseDTO>> listarTodas() {
        List<TrilhaResponseDTO> trilhas = trilhaService.listarTodas();
        return ResponseEntity.ok(trilhas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TrilhaResponseDTO> buscarPorId(@PathVariable Long id) {
        TrilhaResponseDTO trilha = trilhaService.buscarPorId(id);
        return ResponseEntity.ok(trilha);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TrilhaResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody TrilhaRequestDTO requestDTO) {
        TrilhaResponseDTO response = trilhaService.atualizar(id, requestDTO);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        trilhaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
