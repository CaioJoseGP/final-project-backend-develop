package br.com.ifgoiano.gestaopessoas2026.services;

import br.com.ifgoiano.gestaopessoas2026.model.Pessoa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PessoaService {
    
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PessoaService.class.getName());

    public Pessoa buscarPorId(String id) {
        logger.info("Procurando uma pessoa!!!");
        Pessoa person = new Pessoa();
        person.setId(counter.incrementAndGet());
        person.setNome("First MOCK");
        person.setSobrenome("Last MOCK");
        person.setEndereco("Address MOCK");
        person.setGenero("Gender MOCK");
        return person;
    }

    public List<Pessoa> buscarTodas() {
        List<Pessoa> persons = new ArrayList<>();
        logger.info("Buscando Lista de Pessoas");
        for(int i = 0; i < 10; i++) {
            Pessoa person = mockPessoa(i);
            persons.add(person);
        }
        return persons;
    }

    private Pessoa mockPessoa(int i) {
        Pessoa person = new Pessoa();
        person.setId(counter.incrementAndGet());
        person.setNome("First MOCK" + i);
        person.setSobrenome("Last MOCK" + i);
        person.setEndereco("Address MOCK" + i);
        person.setGenero("Gender MOCK" + i);
        return person;
    }

    public Pessoa criar(Pessoa person) {
        logger.info("Criando uma nova entidade na base de dados");
        return person;
    }

    public Pessoa atualizar(Pessoa person) {
        logger.info("Atualizando entidade na base de dados");
        return person;
    }

    public void deletar(String id) {
        logger.info("removendo Entidade na Base de Dados ID..: " + id);
    }
}