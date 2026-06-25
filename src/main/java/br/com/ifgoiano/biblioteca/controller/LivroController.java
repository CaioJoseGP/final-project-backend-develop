package br.com.ifgoiano.biblioteca.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.ifgoiano.biblioteca.model.Livro;
import br.com.ifgoiano.biblioteca.services.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroService service;

    @PostMapping
    public ResponseEntity<Livro> create(@RequestBody Livro livro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(livro));
    }

    @GetMapping
    public ResponseEntity<List<Livro>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> update(@PathVariable Long id, @RequestBody Livro livro) {
        return ResponseEntity.ok(service.update(id, livro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
