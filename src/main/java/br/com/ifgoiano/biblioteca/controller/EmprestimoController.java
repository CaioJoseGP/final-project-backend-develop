package br.com.ifgoiano.biblioteca.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.ifgoiano.biblioteca.model.Emprestimo;
import br.com.ifgoiano.biblioteca.services.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService service;

    @PostMapping
    public ResponseEntity<Emprestimo> create(@RequestBody Emprestimo emprestimo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.realizarEmprestimo(emprestimo));
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<Emprestimo> devolver(@PathVariable Long id) {
        return ResponseEntity.ok(service.devolverEmprestimo(id));
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Emprestimo>> listarAtivos() {
        return ResponseEntity.ok(service.listarAtivos());
    }
}
