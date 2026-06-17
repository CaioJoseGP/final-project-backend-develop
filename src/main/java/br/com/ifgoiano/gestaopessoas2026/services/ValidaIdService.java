package br.com.ifgoiano.gestaopessoas2026.services;

import org.springframework.stereotype.Service;

@Service
public class ValidaIdService {
    public boolean validaId(String id) {
        id = id.trim();

        if(id == null || id.isEmpty()) { return false; } 
        if(!id.matches("\\d+")) { return false; }

        return (convertToLong(id) >= 1);
    }

    public Long convertToLong(String num) {
        return Long.parseLong(num);
    }
}