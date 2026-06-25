CREATE DATABASE IF NOT EXISTS BD_Rest;
USE BD_Rest;

CREATE TABLE autor (
    id_autor BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    nacionalidade VARCHAR(255),
    data_nascimento DATE
);

CREATE TABLE categoria (
    id_categoria BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255)
);

CREATE TABLE membro (
    id_membro BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    endereco VARCHAR(255),
    telefone VARCHAR(255),
    data_cadastro DATE,
    email VARCHAR(255)
);

CREATE TABLE livro (
    id_livro BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) UNIQUE,
    ano_publicacao INT,
    status VARCHAR(50),
    id_autor BIGINT NOT NULL,
    id_categoria BIGINT NOT NULL,
    FOREIGN KEY (id_autor) REFERENCES autor(id_autor) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE emprestimo (
    id_emprestimo BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE,
    id_livro BIGINT NOT NULL,
    id_membro BIGINT NOT NULL,
    FOREIGN KEY (id_livro) REFERENCES livro(id_livro) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (id_membro) REFERENCES membro(id_membro) ON DELETE RESTRICT ON UPDATE CASCADE
);
