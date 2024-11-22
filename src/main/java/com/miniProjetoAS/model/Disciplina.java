package com.miniProjetoAS.model;

public class Disciplina {
    private int id;
    private String nome;
    private String curso;

    public Disciplina(int id, String curso, String nome) {
        this.id = id;
        this.nome = nome;
        this.curso = curso;
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

    public String toString() {
        return id + " - " + nome + " - " + curso+ " \n ";
    }
}
