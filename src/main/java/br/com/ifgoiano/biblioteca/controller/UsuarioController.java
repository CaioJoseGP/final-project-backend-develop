package br.com.ifgoiano.biblioteca.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.ifgoiano.biblioteca.model.Usuario;
import br.com.ifgoiano.biblioteca.services.UsuarioService;

@RestController
@RequestMapping("/membros")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(service.update(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
