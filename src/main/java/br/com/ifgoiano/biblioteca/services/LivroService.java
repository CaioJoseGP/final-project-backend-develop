package br.com.ifgoiano.biblioteca.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ifgoiano.biblioteca.exceptions.ResourceNotFoundException;
import br.com.ifgoiano.biblioteca.model.Livro;
import br.com.ifgoiano.biblioteca.model.StatusLivro;
import br.com.ifgoiano.biblioteca.repositories.LivroRepository;
import br.com.ifgoiano.biblioteca.repositories.AutorRepository;
import br.com.ifgoiano.biblioteca.repositories.CategoriaRepository;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repository;
    
    @Autowired
    private AutorRepository autorRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Livro save(Livro livro) {
        if(livro.getAutor() != null && livro.getAutor().getId() != null) {
            livro.setAutor(autorRepository.findById(livro.getAutor().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Autor não encontrado")
            ));
        }
        if(livro.getCategoria() != null && livro.getCategoria().getId() != null) {
            livro.setCategoria(categoriaRepository.findById(livro.getCategoria().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrada")
            ));
        }
        if (livro.getStatus() == null) {
            livro.setStatus(StatusLivro.DISPONIVEL);
        }
        return repository.save(livro);
    }

    public Livro findById(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Livro não encontrado: " + id)
        );
    }

    public List<Livro> findAll() {
        return repository.findAll();
    }

    public Livro update(Long id, Livro livro) {
        Livro entity = findById(id);
        entity.setTitulo(livro.getTitulo());
        entity.setIsbn(livro.getIsbn());
        entity.setAnoPublicacao(livro.getAnoPublicacao());
        if (livro.getStatus() != null) {
            entity.setStatus(livro.getStatus());
        }
        if (livro.getAutor() != null && livro.getAutor().getId() != null) {
            entity.setAutor(autorRepository.findById(livro.getAutor().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Autor não encontrado")
            ));
        }
        if (livro.getCategoria() != null && livro.getCategoria().getId() != null) {
            entity.setCategoria(categoriaRepository.findById(livro.getCategoria().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrada")
            ));
        }
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }
}
