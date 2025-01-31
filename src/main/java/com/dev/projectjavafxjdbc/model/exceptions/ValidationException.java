package com.dev.projectjavafxjdbc.model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException{
    public static final long serialversionUID = 1L;


    // Map é coleçaão de pares chave-valor
    // estamos fazendo porque vamos ter que guardar os erros
    // de cada campo do formulario
    // por exemplo, para o campo nome o erro é xPto
    // o primeiro string vai representar o campo
    // e o segundo a mensagem de erro
    private Map<String, String> errors = new HashMap<>();

    public ValidationException(String msg) {
        super(msg);
    }

    // Get
    public Map<String, String> getErrors() {
        return errors;
    }

    // Método para adiconar o erro na coleção do MAP

    public void addError(String fieldName, String errorMessage) {
        errors.put(fieldName, errorMessage);
    }
}
