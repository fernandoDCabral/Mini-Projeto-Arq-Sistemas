package com.miniProjetoAS.controller;

import com.miniProjetoAS.model.Estudante;
import com.miniProjetoAS.service.EstudanteService;

import com.miniProjetoAS.model.Livros;
import com.miniProjetoAS.service.LivroService;

import com.miniProjetoAS.microServices.HttpLivros;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerLivro {
    private final LivroService livroService;
    private final HttpLivros servicoLivros = new HttpLivros();
    private final EstudanteService estudanteService = new EstudanteService();

    public ControllerLivro() {
        this.livroService = new LivroService(new HttpLivros());
    }

    public String reservarLivro(int estudanteId, int livroId) {
        Estudante ID = estudanteService.buscarEstudantePorId(estudanteId);
        if (ID != null && "ativo".equalsIgnoreCase(ID.isStatus())) {
            List<Livros> livrosDisponiveis = servicoLivros.obterLivros();
            int LivroId = livroId;
            Livros livroEscolhido = livrosDisponiveis.stream()
                    .filter(l -> l.getId() == livroId)
                    .findFirst()
                    .orElse(null);

            if (livroEscolhido != null && livroService.reservarLivro(ID, livroEscolhido)) {
                return"Reserva realizada com sucesso.";
            } else {
                return"Não foi possível realizar a reserva.";
            }
        } else {
            return "Estudante não encontrado ou não está ativo.";
        }
    }

    public String listarLivrosReservados(int estudanteId) {
        List<Livros> livros = livroService.listarLivrosReservados(estudanteId);
        return livros.isEmpty()
                ? "Nenhum livro reservado para este estudante."
                : livros.stream().map(Livros::toString).collect(Collectors.joining("\n"));
    }

    public String cancelarReserva(int estudanteId, int livroId) {
        boolean sucesso = livroService.cancelarReserva(estudanteId, livroId);
        return sucesso ? "Reserva cancelada com sucesso." : "Não foi possível cancelar a reserva.";
    }

    public void mostrarLivros() {
        List<Livros> livrosDisponiveis = servicoLivros.obterLivros();
        System.out.println("Livros disponíveis para reserva:");
        livrosDisponiveis.forEach(System.out::println);
    }
}
