package br.com.ifgoiano.gestaopessoas2026.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AcessoNaoAutorizadoException extends RuntimeException {
    
    public AcessoNaoAutorizadoException(String message) {
        super(message);
    }
}