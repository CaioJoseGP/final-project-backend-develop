package br.com.ifgoiano.biblioteca.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ifgoiano.biblioteca.exceptions.ResourceNotFoundException;
import br.com.ifgoiano.biblioteca.model.Categoria;
import br.com.ifgoiano.biblioteca.repositories.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    public Categoria save(Categoria categoria) {
        return repository.save(categoria);
    }

    public Categoria findById(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Categoria não encontrada: " + id)
        );
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Categoria update(Long id, Categoria categoria) {
        Categoria entity = findById(id);
        entity.setNome(categoria.getNome());
        entity.setDescricao(categoria.getDescricao());
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }
}
