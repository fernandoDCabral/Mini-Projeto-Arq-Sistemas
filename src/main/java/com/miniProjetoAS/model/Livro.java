package com.miniProjetoAS.model;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private int ano;
    private String reservado;

    public Livro(int id, String titulo, String autor, int ano, String reservado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.reservado = reservado;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAno() {
        return ano;
    }

    public boolean isReservado() {
        return "reservado".equalsIgnoreCase(reservado);
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado ? "reservado" : "disponível";
    }

    public String toString() {
        return id + " " + titulo + " " + autor + " " + ano + " " + reservado + "\n";
    }
}
