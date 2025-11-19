package br.com.pathcat.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    
    public UsuarioNaoEncontradoException(Long id) {
        super("Usuário não encontrado com o ID: " + id);
    }
    
    public UsuarioNaoEncontradoException(String message) {
        super(message);
    }
}
