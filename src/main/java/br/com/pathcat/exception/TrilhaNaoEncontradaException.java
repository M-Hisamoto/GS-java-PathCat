package br.com.pathcat.exception;

public class TrilhaNaoEncontradaException extends RuntimeException {
    
    public TrilhaNaoEncontradaException(Long id) {
        super("Trilha não encontrada com o ID: " + id);
    }
    
    public TrilhaNaoEncontradaException(String message) {
        super(message);
    }
}
