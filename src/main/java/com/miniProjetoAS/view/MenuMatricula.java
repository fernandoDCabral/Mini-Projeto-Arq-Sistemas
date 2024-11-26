package com.miniProjetoAS.view;

import com.miniProjetoAS.controller.ControllerMatricula;

import java.util.Scanner;

public class MenuMatricula {
    private  ControllerMatricula controllerMatricula;
    private final Scanner sc;

    public MenuMatricula() {
        this.controllerMatricula = new ControllerMatricula();
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            exibirMenuDisciplina();
            int opcao = obterOpcaoUsuario();
            if (!processarOpcaoDisciplina(opcao)) {
                break;
            }
        }
    }

    private void exibirMenuDisciplina() {
        System.out.println("\n--- Menu Disciplina ---");
        System.out.println("1. Matricular um estudante em uma disciplina");
        System.out.println("2. Listar disciplinas matriculadas de um estudante");
        System.out.println("3. Remover uma disciplina da matrícula");
        System.out.println("4. Voltar");
    }

    private int obterOpcaoUsuario() {
        System.out.print("Escolha uma opção: ");
        return sc.nextInt();
    }

    private boolean processarOpcaoDisciplina(int opcao) {
        switch (opcao) {
            case 1 -> matricularEstudanteEmDisciplina();
            case 2 -> listarDisciplinasEstudante();
            case 3 -> removerDisciplinaEstudante();
            case 4 -> {
                System.out.println("Voltando ao Menu Central...");
                return false;
            }
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
        return true;
    }

    private void matricularEstudanteEmDisciplina() {
        controllerMatricula.mostrarDisciplinas();
        System.out.print("Digite o ID do estudante: ");
        int estudanteId = sc.nextInt();
        System.out.print("Digite o ID da disciplina: ");
        int disciplinaID = sc.nextInt();
        String resposta = controllerMatricula.matricularEstudante(estudanteId, disciplinaID);
        System.out.println(resposta);
    }

    private void listarDisciplinasEstudante() {
        System.out.print("Digite o ID do estudante: ");
        int estudanteId = sc.nextInt();
        String resposta = controllerMatricula.listarDisciplinasMatriculadas(estudanteId);
        System.out.println(resposta);
    }

    private void removerDisciplinaEstudante() {
        System.out.print("Digite o ID do estudante: ");
        int estudanteId = sc.nextInt();
        System.out.print("Digite o ID da disciplina: ");
        int disciplinaId = sc.nextInt();
        String resposta = controllerMatricula.removerDisciplina(estudanteId, disciplinaId);
        System.out.println(resposta);
    }
}
