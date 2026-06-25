package br.com.ifgoiano.biblioteca.services;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ifgoiano.biblioteca.exceptions.RegraNegocioException;
import br.com.ifgoiano.biblioteca.exceptions.ResourceNotFoundException;
import br.com.ifgoiano.biblioteca.model.Emprestimo;
import br.com.ifgoiano.biblioteca.model.Livro;
import br.com.ifgoiano.biblioteca.model.Usuario;
import br.com.ifgoiano.biblioteca.model.StatusLivro;
import br.com.ifgoiano.biblioteca.repositories.EmprestimoRepository;
import br.com.ifgoiano.biblioteca.repositories.LivroRepository;
import br.com.ifgoiano.biblioteca.repositories.UsuarioRepository;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;
    
    @Autowired
    private LivroRepository livroRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Emprestimo realizarEmprestimo(Emprestimo emprestimo) {
        if (emprestimo.getUsuario() == null || emprestimo.getUsuario().getId() == null) {
            throw new RegraNegocioException("Usuário é obrigatório para o empréstimo.");
        }
        if (emprestimo.getLivro() == null || emprestimo.getLivro().getId() == null) {
            throw new RegraNegocioException("Livro é obrigatório para o empréstimo.");
        }

        Usuario usuario = usuarioRepository.findById(emprestimo.getUsuario().getId()).orElseThrow(
            () -> new ResourceNotFoundException("Usuário não encontrado: " + emprestimo.getUsuario().getId())
        );

        Livro livro = livroRepository.findById(emprestimo.getLivro().getId()).orElseThrow(
            () -> new ResourceNotFoundException("Livro não encontrado: " + emprestimo.getLivro().getId())
        );

        // Regra 1 - Limite de Empréstimos por Usuário
        int emprestimosAtivos = emprestimoRepository.countByUsuarioAndDataDevolucaoIsNull(usuario);
        if (emprestimosAtivos >= 3) {
            throw new RegraNegocioException("O usuário já possui 3 empréstimos ativos e não pode realizar novos empréstimos.");
        }

        // Regra 2 - Controle de Disponibilidade do Livro
        if (livro.getStatus() != StatusLivro.DISPONIVEL) {
            throw new RegraNegocioException("O livro não está disponível para empréstimo.");
        }

        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(null);

        // Atualiza status do livro
        livro.setStatus(StatusLivro.EMPRESTADO);
        livroRepository.save(livro);

        return emprestimoRepository.save(emprestimo);
    }

    @Transactional
    public Emprestimo devolverEmprestimo(Long idEmprestimo) {
        Emprestimo emprestimo = emprestimoRepository.findById(idEmprestimo).orElseThrow(
            () -> new ResourceNotFoundException("Empréstimo não encontrado: " + idEmprestimo)
        );

        if (emprestimo.getDataDevolucao() != null) {
            throw new RegraNegocioException("Este empréstimo já foi devolvido.");
        }

        emprestimo.setDataDevolucao(LocalDate.now());

        Livro livro = emprestimo.getLivro();
        livro.setStatus(StatusLivro.DISPONIVEL);
        livroRepository.save(livro);

        return emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> listarAtivos() {
        return emprestimoRepository.findByDataDevolucaoIsNull();
    }

    public List<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo findById(Long id) {
        return emprestimoRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Empréstimo não encontrado: " + id)
        );
    }
}
