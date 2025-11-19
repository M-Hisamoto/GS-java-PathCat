package br.com.pathcat.controller;

import br.com.pathcat.dto.CompetenciaDTO;
import br.com.pathcat.service.CompetenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/competencias")
@RequiredArgsConstructor
public class CompetenciaController {
    
    private final CompetenciaService competenciaService;
    
    @GetMapping
    public ResponseEntity<List<CompetenciaDTO>> listarTodas() {
        List<CompetenciaDTO> competencias = competenciaService.listarTodas();
        return ResponseEntity.ok(competencias);
    }
}
