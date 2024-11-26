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
        // Usando LivroService para manipular os livros
        this.livroService = new LivroService(new HttpLivros());  // Inicializa o LivroService
        this.bibliotecaService = new BibliotecaService(new HttpLivros());  // Mantém a lógica de biblioteca
        this.estudanteService = new AlunoService();  // Inicializa o serviço do aluno
    }

    // Método para reservar um livro
    public String reservarLivro(int estudanteId, int livroId) {
        Aluno estudante = estudanteService.buscarEstudantePorId(estudanteId);
        if (estudante != null && "ativo".equalsIgnoreCase(estudante.isStatus())) {
            // Buscando o livro pelo ID usando LivroService
            Livro livroEscolhido = livroService.buscarLivroPorId(livroId);

            if (livroEscolhido != null) {
                if (!livroEscolhido.isReservado()) {
                    if (bibliotecaService.reservarLivro(estudante, livroEscolhido)) {
                        livroEscolhido.setReservado(true); // Marca o livro como reservado
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

    // Listar todos os livros reservados por um estudante
    public String listarLivrosReservados(int estudanteId) {
        List<Livro> livros = bibliotecaService.listarLivrosReservados(estudanteId);
        return livros.isEmpty()
                ? "Nenhum livro reservado para este estudante."
                : livros.stream().map(Livro::toString).collect(Collectors.joining("\n"));
    }

    // Cancelar a reserva de um livro
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

    // Exibir todos os livros disponíveis para reserva
    public void mostrarLivros() {
        List<Livro> livrosDisponiveis = livroService.listarLivros();
        System.out.println("Livros disponíveis para reserva:");
        livrosDisponiveis.forEach(System.out::println);
    }
}
