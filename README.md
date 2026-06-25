# 📚 API - Sistema de Gestão de Biblioteca

Este é o projeto final do backend de um Sistema de Gestão de Biblioteca, desenvolvido em Java com Spring Boot. A aplicação gerencia autores, categorias, livros, usuários e o fluxo de empréstimo com validações de regras de negócio.

---

## 🛠️ Tecnologias Utilizadas
- **Java 17**
- **Spring Boot** (Web, Data JPA)
- **MySQL** (Banco de dados relacional)
- **Maven** (Gerenciamento de dependências)

---

## 💾 Banco de Dados

Para preparar o ambiente de dados, certifique-se de ter o MySQL ativo e execute o script SQL abaixo no seu SGBD (ex: phpMyAdmin, MySQL Workbench) para criar o banco, as tabelas e os relacionamentos necessários:

```sql
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
```

---

## 🚀 Como Testar as Rotas Principais (Postman)

A API roda por padrão em `http://localhost:8080`. Abaixo estão as instruções essenciais para testar os **Endpoints Principais** referentes à lógica de locação e listagem. 

*(Obs: Cadastre alguns Autores, Categorias e Livros antes de prosseguir com os testes de locação).*

### 1️⃣ Criar um Usuário (Membro)
**`POST`** `/membros`
```json
{
  "nome": "Caio José",
  "endereco": "Rua Exemplo, 123",
  "telefone": "6299999999",
  "dataCadastro": "2026-06-25",
  "email": "caio@exemplo.com"
}
```

### 2️⃣ Buscar Usuário
**`GET`** `/membros/1`

### 3️⃣ Realizar um Empréstimo
**`POST`** `/emprestimos`

> ⚠️ **Regras de Negócio Testadas:** Um usuário não pode ter mais de **3 empréstimos ativos**. O livro alvo também deve constar como **`DISPONIVEL`**. Se tentar alugar 4 vezes, ou tentar alugar um livro já locado, a API retorna o erro **HTTP 400 (Bad Request)** informando o motivo.
```json
{
  "livro": {
    "id": 1
  },
  "usuario": {
    "id": 1
  }
}
```

### 4️⃣ Listar Empréstimos Ativos
**`GET`** `/emprestimos/ativos`
*(Retorna a lista de todas as locações em que os livros não foram devolvidos ainda, ou seja, com a data de devolução nula).*

### 5️⃣ Devolver um Livro
**`PUT`** `/emprestimos/1/devolver`
*Não é necessário enviar um payload JSON no body.* A API processa essa rota e automaticamente preencherá a data atual na devolução do empréstimo de ID `1` e mudará o status do respectivo livro de volta para `DISPONIVEL`.
