package br.com.ifgoiano.gestaopessoas2026.controller;

import br.com.ifgoiano.gestaopessoas2026.exceptions.AcessoNaoAutorizadoException;
import br.com.ifgoiano.gestaopessoas2026.exceptions.RequisicaoInvalidaException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteExcecoesController {

    @RequestMapping("/int/{v1}")
    public char internalServerError(@PathVariable(value = "v1") char v1) throws Exception {
        if (v1 == 'a') {
            throw new Exception("Erro Interno de Teste");
        }
        return v1;
    }

    @RequestMapping("/bad/{v1}")
    public char badRequest(@PathVariable(value = "v1") char v1) {
        if (v1 == 'b') {
            throw new RequisicaoInvalidaException("Requisicao Invalida de Teste");
        }
        return v1;
    }

    @RequestMapping("/nao/{v1}")
    public char unauthorized(@PathVariable(value = "v1") char v1) {
        if (v1 == 'c') {
            throw new AcessoNaoAutorizadoException("Acesso Nao Autorizado de Teste");
        }
        return v1;
    }
}