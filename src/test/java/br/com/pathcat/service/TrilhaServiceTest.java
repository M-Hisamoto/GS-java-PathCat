package br.com.pathcat.service;

import br.com.pathcat.domain.Competencia;
import br.com.pathcat.domain.NivelTrilha;
import br.com.pathcat.domain.Trilha;
import br.com.pathcat.dto.TrilhaRequestDTO;
import br.com.pathcat.dto.TrilhaResponseDTO;
import br.com.pathcat.exception.TrilhaNaoEncontradaException;
import br.com.pathcat.repository.CompetenciaRepository;
import br.com.pathcat.repository.TrilhaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrilhaServiceTest {

    @Mock
    private TrilhaRepository trilhaRepository;

    @Mock
    private CompetenciaRepository competenciaRepository;

    @InjectMocks
    private TrilhaService trilhaService;

    private Trilha trilha;
    private TrilhaRequestDTO requestDTO;
    private Competencia competencia;

    @BeforeEach
    void setUp() {
        competencia = new Competencia();
        competencia.setId(1L);
        competencia.setNome("Test Competencia");

        trilha = new Trilha();
        trilha.setId(1L);
        trilha.setNome("Test Trilha");
        trilha.setDescricao("Test Description");
        trilha.setNivel(NivelTrilha.INTERMEDIARIO);
        trilha.setCargaHoraria(40);
        trilha.setFocoPrincipal("Test Focus");
        trilha.setCompetencias(new HashSet<>(Arrays.asList(competencia)));

        requestDTO = new TrilhaRequestDTO();
        requestDTO.setNome("Test Trilha");
        requestDTO.setDescricao("Test Description");
        requestDTO.setNivel(NivelTrilha.INTERMEDIARIO);
        requestDTO.setCargaHoraria(40);
        requestDTO.setFocoPrincipal("Test Focus");
        requestDTO.setCompetenciasIds(Set.of(1L));
    }

    @Test
    void testCriarTrilha() {
        when(competenciaRepository.findById(1L)).thenReturn(Optional.of(competencia));
        when(trilhaRepository.save(any(Trilha.class))).thenReturn(trilha);

        TrilhaResponseDTO response = trilhaService.criar(requestDTO);

        assertNotNull(response);
        assertEquals("Test Trilha", response.getNome());
        assertEquals(NivelTrilha.INTERMEDIARIO, response.getNivel());
        verify(trilhaRepository, times(1)).save(any(Trilha.class));
    }

    @Test
    void testListarTodasTrilhas() {
        List<Trilha> trilhas = Arrays.asList(trilha);
        when(trilhaRepository.findAll()).thenReturn(trilhas);

        List<TrilhaResponseDTO> responses = trilhaService.listarTodas();

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Test Trilha", responses.get(0).getNome());
        verify(trilhaRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId() {
        when(trilhaRepository.findById(1L)).thenReturn(Optional.of(trilha));

        TrilhaResponseDTO response = trilhaService.buscarPorId(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Test Trilha", response.getNome());
        verify(trilhaRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorIdNaoEncontrado() {
        when(trilhaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(TrilhaNaoEncontradaException.class, () -> {
            trilhaService.buscarPorId(999L);
        });

        verify(trilhaRepository, times(1)).findById(999L);
    }

    @Test
    void testDeletarTrilha() {
        when(trilhaRepository.existsById(1L)).thenReturn(true);
        doNothing().when(trilhaRepository).deleteById(1L);

        assertDoesNotThrow(() -> trilhaService.deletar(1L));

        verify(trilhaRepository, times(1)).existsById(1L);
        verify(trilhaRepository, times(1)).deleteById(1L);
    }
}
