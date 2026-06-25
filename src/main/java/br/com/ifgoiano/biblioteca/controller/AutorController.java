package br.com.ifgoiano.biblioteca.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.ifgoiano.biblioteca.model.Autor;
import br.com.ifgoiano.biblioteca.services.AutorService;

@RestController
@RequestMapping("/autores")
public class AutorController {
    @Autowired
    private AutorService service;

    @PostMapping
    public ResponseEntity<Autor> create(@RequestBody Autor autor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(autor));
    }

    @GetMapping
    public ResponseEntity<List<Autor>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> update(@PathVariable Long id, @RequestBody Autor autor) {
        return ResponseEntity.ok(service.update(id, autor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
