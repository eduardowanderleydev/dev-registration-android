package br.com.ucsal.devregistration.exception;

/**
 * Excessão que deve ser lançada quando o usuário digitar um CEP inválido.
 */
public class InvalidCEPException extends Exception {
    public InvalidCEPException(String message) {
        super(message);
    }
}
