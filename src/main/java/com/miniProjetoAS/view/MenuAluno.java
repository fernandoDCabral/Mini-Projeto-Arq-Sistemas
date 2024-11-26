package com.miniProjetoAS.view;

import com.miniProjetoAS.controller.ControllerAluno;

import java.util.Scanner;

public class MenuAluno {
    private final ControllerAluno controllerAluno;
    private final Scanner sc;

    public MenuAluno() {
        this.controllerAluno = new ControllerAluno();
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            exibirMenuAluno();
            int opcao = obterOpcaoUsuario();
            if (!processarOpcaoAluno(opcao)) {
                break;
            }
        }
    }

    private void exibirMenuAluno() {
        System.out.println("\n--- Menu Aluno ---");
        System.out.println("1. Listar estudantes do curso de 'História' (modalidade presencial)");
        System.out.println("2. Ver detalhes de um estudante por ID");
        System.out.println("3. Ver detalhes de um estudante por nome");
        System.out.println("4. Voltar");
    }

    private int obterOpcaoUsuario() {
        System.out.print("Escolha uma opção: ");
        return sc.nextInt();
    }

    private boolean processarOpcaoAluno(int opcao) {
        switch (opcao) {
            case 1 -> listarEstudantesDeHistoriaPresencial();
            case 2 -> verDetalhesEstudantePorId();
            case 3 -> verDetalhesEstudantePorNome();
            case 4 -> {
                System.out.println("Voltando ao Menu Central...");
                return false;
            }
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
        return true;
    }

    private void listarEstudantesDeHistoriaPresencial() {
        String resposta = controllerAluno.listarEstudantesDeHistoriaPresencial();
        System.out.println(resposta);
    }

    private void verDetalhesEstudantePorId() {
        System.out.print("Digite o ID do estudante: ");
        int id = sc.nextInt();
        String resposta = controllerAluno.buscarEstudantePorId(id);
        System.out.println(resposta);
    }

    private void verDetalhesEstudantePorNome() {
        System.out.print("Digite o nome do estudante: ");
        sc.nextLine();
        String nome = sc.nextLine();
        String resposta = controllerAluno.buscarEstudantePorNome(nome);
        System.out.println(resposta);
    }
}
