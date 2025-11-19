package br.com.pathcat.service;

import br.com.pathcat.domain.Usuario;
import br.com.pathcat.dto.UsuarioRequestDTO;
import br.com.pathcat.dto.UsuarioResponseDTO;
import br.com.pathcat.exception.UsuarioNaoEncontradoException;
import br.com.pathcat.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    
    @Transactional
    public UsuarioResponseDTO criar(UsuarioRequestDTO requestDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(requestDTO.getNome());
        usuario.setEmail(requestDTO.getEmail());
        usuario.setAreaAtuacao(requestDTO.getAreaAtuacao());
        usuario.setNivelCarreira(requestDTO.getNivelCarreira());
        
        Usuario salvo = usuarioRepository.save(usuario);
        return toResponseDTO(salvo);
    }
    
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
        return toResponseDTO(usuario);
    }
    
    @Transactional
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO requestDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
        
        usuario.setNome(requestDTO.getNome());
        usuario.setEmail(requestDTO.getEmail());
        usuario.setAreaAtuacao(requestDTO.getAreaAtuacao());
        usuario.setNivelCarreira(requestDTO.getNivelCarreira());
        
        Usuario atualizado = usuarioRepository.save(usuario);
        return toResponseDTO(atualizado);
    }
    
    @Transactional
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNaoEncontradoException(id);
        }
        usuarioRepository.deleteById(id);
    }
    
    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getAreaAtuacao(),
                usuario.getNivelCarreira(),
                usuario.getDataCadastro()
        );
    }
}
