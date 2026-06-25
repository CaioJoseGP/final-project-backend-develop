package br.com.ifgoiano.biblioteca.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ifgoiano.biblioteca.exceptions.ResourceNotFoundException;
import br.com.ifgoiano.biblioteca.model.Autor;
import br.com.ifgoiano.biblioteca.repositories.AutorRepository;

@Service
public class AutorService {
    @Autowired
    private AutorRepository repository;

    public Autor save(Autor autor) {
        return repository.save(autor);
    }

    public Autor findById(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Autor não encontrado: " + id)
        );
    }

    public List<Autor> findAll() {
        return repository.findAll();
    }

    public Autor update(Long id, Autor autor) {
        Autor entity = findById(id);
        entity.setNome(autor.getNome());
        entity.setNacionalidade(autor.getNacionalidade());
        entity.setDataNascimento(autor.getDataNascimento());
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }
}
