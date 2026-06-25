package br.com.ifgoiano.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ifgoiano.biblioteca.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
