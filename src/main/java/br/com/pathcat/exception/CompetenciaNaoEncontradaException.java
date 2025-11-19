package br.com.pathcat.exception;

public class CompetenciaNaoEncontradaException extends RuntimeException {
    
    public CompetenciaNaoEncontradaException(Long id) {
        super("Competência não encontrada com o ID: " + id);
    }
    
    public CompetenciaNaoEncontradaException(String message) {
        super(message);
    }
}
