package com.miniProjetoAS.view;

import java.util.Scanner;

public class MenuGeral {
    private final MenuAluno menuAluno;
    private final MenuMatricula menuMatricula;
    private final MenuBiblioteca menuBiblioteca;
    private final Scanner sc;

    public MenuGeral() {
        this.menuAluno = new MenuAluno();
        this.menuMatricula = new MenuMatricula();
        this.menuBiblioteca = new MenuBiblioteca();
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            exibirMenuCentral();
            int opcao = obterOpcaoUsuario();
            if (!processarOpcaoCentral(opcao)) {
                break;
            }
        }
    }

    private void exibirMenuCentral() {
        System.out.println("\n--- Menu Central ---");
        System.out.println("1. Gerenciar Alunos");
        System.out.println("2. Gerenciar Disciplinas");
        System.out.println("3. Gerenciar Livros");
        System.out.println("4. Sair");
    }

    private int obterOpcaoUsuario() {
        System.out.print("Escolha uma opção: ");
        return sc.nextInt();
    }

    private boolean processarOpcaoCentral(int opcao) {
        switch (opcao) {
            case 1 -> menuAluno.iniciar();
            case 2 -> menuMatricula.iniciar();
            case 3 -> menuBiblioteca.iniciar();
            case 4 -> {
                System.out.println("Saindo...");
                return false;
            }
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
        return true;
    }
}
