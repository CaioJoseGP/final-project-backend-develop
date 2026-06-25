package br.com.ifgoiano.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ifgoiano.biblioteca.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
