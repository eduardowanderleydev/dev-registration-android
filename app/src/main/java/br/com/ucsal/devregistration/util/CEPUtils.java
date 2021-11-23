package br.com.ucsal.devregistration.util;

import br.com.ucsal.devregistration.exception.InvalidCEPException;

/**
 * Classe responsável por cuidar da validação do CEP digitado pelo usuário.
 */
public class CEPUtils {

    public static boolean validaCEP(String cep) throws InvalidCEPException {
        if (cep == null || cep.isEmpty() ){
            throw new InvalidCEPException("Por favor preencha o campo CEP.");
        }

        if (cep.matches("[0-9]{8}")) {
            return true;
        } else {
            throw new InvalidCEPException("Por favor, insira um cep válido");
        }
    }

}
