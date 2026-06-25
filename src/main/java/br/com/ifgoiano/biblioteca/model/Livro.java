package br.com.ifgoiano.biblioteca.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "livro")
public class Livro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livro")
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "isbn", unique = true)
    private String isbn;

    @Column(name = "ano_publicacao")
    private Integer anoPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusLivro status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor", nullable = false)
    @JsonBackReference
    private Autor autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    @JsonBackReference
    private Categoria categoria;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public Livro() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Integer getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(Integer anoPublicacao) { this.anoPublicacao = anoPublicacao; }

    public StatusLivro getStatus() { return status; }
    public void setStatus(StatusLivro status) { this.status = status; }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public List<Emprestimo> getEmprestimos() { return emprestimos; }
    public void setEmprestimos(List<Emprestimo> emprestimos) { this.emprestimos = emprestimos; }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, isbn, anoPublicacao, status);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Livro other = (Livro) obj;
        return Objects.equals(id, other.id) && Objects.equals(titulo, other.titulo)
                && Objects.equals(isbn, other.isbn) && Objects.equals(anoPublicacao, other.anoPublicacao)
                && status == other.status;
    }
}
