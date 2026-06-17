package br.com.ifgoiano.gestaopessoas2026.services;

import br.com.ifgoiano.gestaopessoas2026.exceptions.ResourceNotFoundException;
import br.com.ifgoiano.gestaopessoas2026.model.Pessoa;
import br.com.ifgoiano.gestaopessoas2026.repositories.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PessoaService {
    private Logger logger = Logger.getLogger(PessoaService.class.getName());

    @Autowired
    PersonRepository repository;

    public Pessoa buscarPorId(Long id) {
        logger.info("Procurando uma pessoa!!!");
        return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Não há registros para esse ID"));
    }

    public List<Pessoa> buscarTodas() {
        logger.info("Buscando Lista de Pessoas");
        return repository.findAll();
    }

    public Pessoa criar(Pessoa person) {
        logger.info("Criando uma nova entidade na base de dados");
        return repository.save(person);
    }

    public Pessoa atualizar(Pessoa person) {
        logger.info("Atualizando entidade na base de dados");
        
        var entity = repository.findById(person.getId()).orElseThrow(()-> new ResourceNotFoundException("Não há registros para esse ID"));
        entity.setNome(person.getNome());
        entity.setSobrenome(person.getSobrenome());
        entity.setEndereco(person.getEndereco());
        entity.setGenero(person.getGenero());

        return repository.save(entity);
    }

    public void deletar(Long id) {
        logger.info("removendo Entidade na Base de Dados ID..: " + id);

        var entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Não há Registros com esse ID"));

        repository.delete(entity);
    }
}