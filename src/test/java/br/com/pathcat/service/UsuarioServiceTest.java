package br.com.pathcat.service;

import br.com.pathcat.domain.Usuario;
import br.com.pathcat.domain.NivelCarreira;
import br.com.pathcat.dto.UsuarioRequestDTO;
import br.com.pathcat.dto.UsuarioResponseDTO;
import br.com.pathcat.exception.UsuarioNaoEncontradoException;
import br.com.pathcat.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private UsuarioRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Test User");
        usuario.setEmail("test@example.com");
        usuario.setAreaAtuacao("Test Area");
        usuario.setNivelCarreira(NivelCarreira.JUNIOR);
        usuario.setDataCadastro(LocalDateTime.now());

        requestDTO = new UsuarioRequestDTO();
        requestDTO.setNome("Test User");
        requestDTO.setEmail("test@example.com");
        requestDTO.setAreaAtuacao("Test Area");
        requestDTO.setNivelCarreira(NivelCarreira.JUNIOR);
    }

    @Test
    void testCriarUsuario() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioResponseDTO response = usuarioService.criar(requestDTO);

        assertNotNull(response);
        assertEquals("Test User", response.getNome());
        assertEquals("test@example.com", response.getEmail());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testListarTodosUsuarios() {
        List<Usuario> usuarios = Arrays.asList(usuario);
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<UsuarioResponseDTO> responses = usuarioService.listarTodos();

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Test User", responses.get(0).getNome());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        UsuarioResponseDTO response = usuarioService.buscarPorId(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Test User", response.getNome());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorIdNaoEncontrado() {
        when(usuarioRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            usuarioService.buscarPorId(999L);
        });

        verify(usuarioRepository, times(1)).findById(999L);
    }

    @Test
    void testAtualizarUsuario() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioResponseDTO response = usuarioService.atualizar(1L, requestDTO);

        assertNotNull(response);
        assertEquals("Test User", response.getNome());
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testDeletarUsuario() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        doNothing().when(usuarioRepository).deleteById(1L);

        assertDoesNotThrow(() -> usuarioService.deletar(1L));

        verify(usuarioRepository, times(1)).existsById(1L);
        verify(usuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeletarUsuarioNaoEncontrado() {
        when(usuarioRepository.existsById(999L)).thenReturn(false);

        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            usuarioService.deletar(999L);
        });

        verify(usuarioRepository, times(1)).existsById(999L);
        verify(usuarioRepository, never()).deleteById(999L);
    }
}
