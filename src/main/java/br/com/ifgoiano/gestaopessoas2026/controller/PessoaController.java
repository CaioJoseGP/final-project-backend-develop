package br.com.ifgoiano.gestaopessoas2026.controller;

import br.com.ifgoiano.gestaopessoas2026.model.Pessoa;
import br.com.ifgoiano.gestaopessoas2026.services.PessoaService;
import br.com.ifgoiano.gestaopessoas2026.services.ValidaIdService;
import br.com.ifgoiano.gestaopessoas2026.exceptions.RequisicaoInvalidaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @Autowired
    private ValidaIdService valida;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa buscarPorId(@PathVariable(value = "id") String id) throws Exception {
        if(!valida.validaId(id)) {
            throw new RequisicaoInvalidaException("ID não é Válido");
        }

        Long idBD = valida.convertToLong(id);
        return service.buscarPorId(idBD);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pessoa> buscarTodas() {
        return service.buscarTodas();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa criar(@RequestBody Pessoa pessoa) {
        return service.criar(pessoa);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa atualizar(@RequestBody Pessoa pessoa) {
        return service.atualizar(pessoa);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable(value = "id") String id) throws Exception {
        if(!valida.validaId(id)) {
            throw new RequisicaoInvalidaException("ID não é Válido"); 
        }

        Long idBD = valida.convertToLong(id);
        service.deletar(idBD);
        return ResponseEntity.noContent().build();
    }
}