package br.com.ifgoiano.gestaopessoas2026.controller;

import br.com.ifgoiano.gestaopessoas2026.model.Pessoa;
import br.com.ifgoiano.gestaopessoas2026.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa buscarPorId(@PathVariable(value = "id") String id) {
        return service.buscarPorId(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pessoa> buscarTodas() {
        return service.buscarTodas();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa criar(@RequestBody Pessoa pessoa) {
        return service.criar(pessoa);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa atualizar(@RequestBody Pessoa pessoa) {
        return service.atualizar(pessoa);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletar(@PathVariable(value = "id") String id) {
        service.deletar(id);
    }
}