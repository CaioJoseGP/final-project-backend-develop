package br.com.ifgoiano.biblioteca.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ifgoiano.biblioteca.model.Emprestimo;
import br.com.ifgoiano.biblioteca.model.Usuario;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    int countByUsuarioAndDataDevolucaoIsNull(Usuario usuario);
    List<Emprestimo> findByDataDevolucaoIsNull();
}
