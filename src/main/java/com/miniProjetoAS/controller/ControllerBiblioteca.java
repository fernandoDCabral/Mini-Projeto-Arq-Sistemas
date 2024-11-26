package com.miniProjetoAS.controller;

import com.miniProjetoAS.model.Aluno;
import com.miniProjetoAS.service.AlunoService;

import com.miniProjetoAS.model.Livro;
import com.miniProjetoAS.service.BibliotecaService;

import com.miniProjetoAS.microServices.HttpLivros;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerBiblioteca {
    private final BibliotecaService livroService;
    private final HttpLivros servicoLivros = new HttpLivros();
    private final AlunoService estudanteService = new AlunoService();

    public ControllerBiblioteca() {
        this.livroService = new BibliotecaService(new HttpLivros());
    }

    public String reservarLivro(int estudanteId, int livroId) {
        Aluno ID = estudanteService.buscarEstudantePorId(estudanteId);
        if (ID != null && "ativo".equalsIgnoreCase(ID.isStatus())) {
            List<Livro> livrosDisponiveis = servicoLivros.obterLivros();//ir para controllerLivros
            int LivroId = livroId;
            Livro livroEscolhido = livrosDisponiveis.stream()
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
        List<Livro> livros = livroService.listarLivrosReservados(estudanteId);
        return livros.isEmpty()
                ? "Nenhum livro reservado para este estudante."
                : livros.stream().map(Livro::toString).collect(Collectors.joining("\n"));
    }

    public String cancelarReserva(int estudanteId, int livroId) {
        boolean sucesso = livroService.cancelarReserva(estudanteId, livroId);
        return sucesso ? "Reserva cancelada com sucesso." : "Não foi possível cancelar a reserva.";
    }


    //ir para controllerLivro
    public void mostrarLivros() {
        List<Livro> livrosDisponiveis = servicoLivros.obterLivros();
        System.out.println("Livros disponíveis para reserva:");
        livrosDisponiveis.forEach(System.out::println);
    }
}
