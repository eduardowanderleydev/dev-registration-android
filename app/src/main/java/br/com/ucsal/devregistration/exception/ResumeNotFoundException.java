package br.com.ucsal.devregistration.exception;

/**
 * Excessão que deve ser lançada quando o Currículo não for encontrado.
 */
public class ResumeNotFoundException extends RuntimeException {
    public ResumeNotFoundException(String message) {
        super(message);
    }
}
