package com.miniProjetoAS.model;

public class Estudante {
    private int id;
    private String nome;
    private String curso;
    private String modalidade;
    private String status;

    public Estudante(int id, String nome, String curso, String modalidade, String status) {
        this.id = id;
        this.nome = nome;
        this.curso = curso;
        this.modalidade = modalidade;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCurso() {
        return curso;
    }

    public String getModalidade() {
        return modalidade;
    }

    public String isStatus() {
        return status;
    }

    public String toString() {
        return id + " " + nome + " " + curso + " " + modalidade + " " + status + "\n";
    }
}
