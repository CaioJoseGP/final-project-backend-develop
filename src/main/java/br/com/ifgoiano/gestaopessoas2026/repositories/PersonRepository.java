package br.com.ifgoiano.gestaopessoas2026.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ifgoiano.gestaopessoas2026.model.Pessoa;

public interface PersonRepository extends JpaRepository<Pessoa, Long> {
    
}
