package br.com.ifgoiano.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ifgoiano.biblioteca.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
