package br.com.ifgoiano.biblioteca.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ifgoiano.biblioteca.exceptions.ResourceNotFoundException;
import br.com.ifgoiano.biblioteca.model.Usuario;
import br.com.ifgoiano.biblioteca.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario findById(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Usuário não encontrado: " + id)
        );
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario update(Long id, Usuario usuario) {
        Usuario entity = findById(id);
        entity.setNome(usuario.getNome());
        entity.setEndereco(usuario.getEndereco());
        entity.setTelefone(usuario.getTelefone());
        entity.setDataCadastro(usuario.getDataCadastro());
        entity.setEmail(usuario.getEmail());
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }
}
