package br.com.pathcat.controller;

import br.com.pathcat.dto.MatriculaRequestDTO;
import br.com.pathcat.dto.MatriculaResponseDTO;
import br.com.pathcat.dto.TrilhaResponseDTO;
import br.com.pathcat.dto.UsuarioResponseDTO;
import br.com.pathcat.service.MatriculaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
@RequiredArgsConstructor
public class MatriculaController {
    
    private final MatriculaService matriculaService;
    
    @PostMapping
    public ResponseEntity<MatriculaResponseDTO> criar(@Valid @RequestBody MatriculaRequestDTO requestDTO) {
        MatriculaResponseDTO response = matriculaService.criar(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("/usuarios/{usuarioId}/trilhas")
    public ResponseEntity<List<TrilhaResponseDTO>> listarTrilhasPorUsuario(@PathVariable Long usuarioId) {
        List<TrilhaResponseDTO> trilhas = matriculaService.listarTrilhasPorUsuario(usuarioId);
        return ResponseEntity.ok(trilhas);
    }
    
    @GetMapping("/trilhas/{trilhaId}/usuarios")
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuariosPorTrilha(@PathVariable Long trilhaId) {
        List<UsuarioResponseDTO> usuarios = matriculaService.listarUsuariosPorTrilha(trilhaId);
        return ResponseEntity.ok(usuarios);
    }
}
