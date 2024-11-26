package com.miniProjetoAS.view;

import com.miniProjetoAS.controller.ControllerBiblioteca;

import java.util.Scanner;

public class MenuBiblioteca {
    private final ControllerBiblioteca controllerBiblioteca;
    private final Scanner sc;

    public MenuBiblioteca() {
        this.controllerBiblioteca = new ControllerBiblioteca();
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            exibirMenuLivro();
            int opcao = obterOpcaoUsuario();
            if (!processarOpcaoLivro(opcao)) {
                break;
            }
        }
    }

    private void exibirMenuLivro() {
        System.out.println("\n--- Menu Livros ---");
        System.out.println("1. Reservar um livro");
        System.out.println("2. Listar livros reservados por um estudante");
        System.out.println("3. Cancelar a reserva de um livro");
        System.out.println("4. Voltar ao Menu Principal");
    }

    private int obterOpcaoUsuario() {
        System.out.print("Escolha uma opção: ");
        return sc.nextInt();
    }

    private boolean processarOpcaoLivro(int opcao) {
        switch (opcao) {
            case 1 -> reservarLivro();
            case 2 -> listarLivrosReservados();
            case 3 -> cancelarReservaLivro();
            case 4 -> {
                System.out.println("Voltando ao Menu Principal...");
                return false;
            }
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
        return true;
    }

    private void reservarLivro() {
        controllerBiblioteca.mostrarLivros();
        System.out.print("Digite o ID do estudante: ");
        int estudanteId = sc.nextInt();
        System.out.print("Digite o ID do livro: ");
        int livroId = sc.nextInt();
        String resposta = controllerBiblioteca.reservarLivro(estudanteId, livroId);
        System.out.println(resposta);
    }

    private void listarLivrosReservados() {
        System.out.print("Digite o ID do estudante: ");
        int estudanteId = sc.nextInt();
        String resposta = controllerBiblioteca.listarLivrosReservados(estudanteId);
        System.out.println(resposta);
    }

    private void cancelarReservaLivro() {
        System.out.print("Digite o ID do estudante: ");
        int estudanteId = sc.nextInt();
        System.out.print("Digite o ID do livro: ");
        int livroId = sc.nextInt();
        String resposta = controllerBiblioteca.cancelarReserva(estudanteId, livroId);
        System.out.println(resposta);
    }
}
