package com.miniProjetoAS.controller;

import com.miniProjetoAS.microServices.HttpLivros;
import com.miniProjetoAS.model.Aluno;
import com.miniProjetoAS.model.Livro;
import com.miniProjetoAS.service.LivroService;
import com.miniProjetoAS.service.BibliotecaService;
import com.miniProjetoAS.service.AlunoService;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerBiblioteca {
    private final LivroService livroService;
    private final BibliotecaService bibliotecaService;
    private final AlunoService estudanteService;

    public ControllerBiblioteca() {
        this.livroService = new LivroService(new HttpLivros());
        this.bibliotecaService = new BibliotecaService(new HttpLivros());
        this.estudanteService = new AlunoService();
    }

    public String reservarLivro(int estudanteId, int livroId) {
        Aluno estudante = estudanteService.buscarEstudantePorId(estudanteId);
        if (estudante != null && "ativo".equalsIgnoreCase(estudante.isStatus())) {

            Livro livroEscolhido = livroService.buscarLivroPorId(livroId);

            if (livroEscolhido != null) {
                if (!livroEscolhido.isReservado()) {
                    if (bibliotecaService.reservarLivro(estudante, livroEscolhido)) {
                        livroEscolhido.setReservado(true);
                        return "Reserva realizada com sucesso.";
                    } else {
                        return "Erro ao registrar a reserva.";
                    }
                } else {
                    return "O livro já está reservado.";
                }
            } else {
                return "Livro não encontrado.";
            }
        } else {
            return "Estudante não encontrado ou não está ativo.";
        }
    }

    public String listarLivrosReservados(int estudanteId) {
        List<Livro> livros = bibliotecaService.listarLivrosReservados(estudanteId);
        return livros.isEmpty()
                ? "Nenhum livro reservado para este estudante."
                : livros.stream().map(Livro::toString).collect(Collectors.joining("\n"));
    }


    public String cancelarReserva(int estudanteId, int livroId) {
        Livro livroEscolhido = livroService.buscarLivroPorId(livroId);

        if (livroEscolhido != null && livroEscolhido.isReservado()) {
            if (bibliotecaService.cancelarReserva(estudanteId, livroId)) {
                livroEscolhido.setReservado(false); // Marca o livro como disponível
                return "Reserva cancelada com sucesso.";
            } else {
                return "Erro ao cancelar a reserva.";
            }
        } else {
            return "Livro não está reservado ou não encontrado.";
        }
    }

    public void mostrarLivros() {
        List<Livro> livrosDisponiveis = livroService.listarLivros();
        System.out.println("Livros disponíveis para reserva:");
        livrosDisponiveis.forEach(System.out::println);
    }
}
