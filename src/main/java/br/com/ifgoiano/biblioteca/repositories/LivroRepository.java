package br.com.ifgoiano.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ifgoiano.biblioteca.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    boolean existsByIsbn(String isbn);
}
