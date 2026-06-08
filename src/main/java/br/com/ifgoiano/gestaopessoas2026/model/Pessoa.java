package br.com.ifgoiano.gestaopessoas2026.model;

import java.io.Serializable;
import java.util.Objects;

public class Pessoa implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String sobrenome;
    private String endereco;
    private String genero;

    public Pessoa() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id) &&
               Objects.equals(nome, pessoa.nome) &&
               Objects.equals(sobrenome, pessoa.sobrenome) &&
               Objects.equals(endereco, pessoa.endereco) &&
               Objects.equals(genero, pessoa.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sobrenome, endereco, genero);
    }
}