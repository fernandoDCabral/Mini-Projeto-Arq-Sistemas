package com.miniProjetoAS.view;

import com.miniProjetoAS.controller.ControllerEstudante;
import com.miniProjetoAS.controller.ControllerDisciplina;
import com.miniProjetoAS.controller.ControllerLivro;

import com.miniProjetoAS.service.LivroService;
import com.miniProjetoAS.service.DisciplinaService;

import com.miniProjetoAS.microServices.servicoLivros;
import com.miniProjetoAS.microServices.servicoDisciplinas;



import java.util.List;
import java.util.Scanner;

public class Menu {

    private  ControllerEstudante controllerEstudante;
    private  ControllerDisciplina controllerDisciplina;
    private  ControllerLivro controllerLivro;
    private  Scanner sc = new Scanner(System.in);

    public Menu() {

        DisciplinaService matriculaService = new DisciplinaService(new servicoDisciplinas());
        LivroService livroService = new LivroService(new servicoLivros());

        controllerEstudante = new ControllerEstudante();
        controllerDisciplina = new ControllerDisciplina(matriculaService);
        controllerLivro = new ControllerLivro(livroService);
    }

    public void iniciar() {
        while (true) {
            exibirMenu();
            int opcao = obterOpcaoUsuario();
            if (!processarOpcao(opcao)) {
                break;
            }
        }
    }

    private void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1. Listar estudantes do curso de 'História' (modalidade presencial)");
        System.out.println("2. Ver detalhes de um estudante por ID");
        System.out.println("3. Ver detalhes de um estudante por nome");
        System.out.println("4. Matricular um estudante em uma disciplina");
        System.out.println("5. Listar disciplinas em que um estudante está matriculado");
        System.out.println("6. Remover uma disciplina da matrícula do estudante");
        System.out.println("7. Reservar um livro");
        System.out.println("8. Listar todos os livros reservados pelo estudante");
        System.out.println("9. Cancelar a reserva de um livro");
        System.out.println("10. Sair");
    }

    private int obterOpcaoUsuario() {
        System.out.print("Digite sua opção: ");
        return sc.nextInt();
    }

    private boolean processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> listarEstudantesDeHistoriaPresencial();
            case 2 -> verDetalhesEstudantePorId();
            case 3 -> verDetalhesEstudantePorNome();

            case 4 -> matricularEstudanteEmDisciplina();
            case 5 -> listarDisciplinasEstudante();
            case 6 -> removerDisciplinaEstudante();

            case 7 -> reservarLivro();
            case 8 -> listarLivrosReservados();
            case 9 -> cancelarReservaLivro();

            case 10 -> {
                System.out.println("Finalizando ...");
                return false;
            }
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
        return true;
    }

    private void listarEstudantesDeHistoriaPresencial() {
        String resposta = controllerEstudante.listarEstudantesDeHistoriaPresencial();
        System.out.println(resposta);
    }

    private void verDetalhesEstudantePorId() {
        System.out.print("Digite o ID do estudante: ");
        int id = sc.nextInt();
        String resposta = controllerEstudante.buscarEstudantePorId(id);
        System.out.println(resposta);
    }

    private void verDetalhesEstudantePorNome() {
        System.out.print("Digite o nome do estudante: ");
        sc.nextLine();
        String nome = sc.nextLine();
        String resposta = controllerEstudante.buscarEstudantePorNome(nome);
        System.out.println(resposta);
    }

    private void matricularEstudanteEmDisciplina() {
        controllerDisciplina.mostrarDisciplinas();
        System.out.print("Digite o ID do estudante: ");
        int estudanteId = sc.nextInt();
        System.out.print("Digite o ID da disciplina: ");
        int disciplinaID = sc.nextInt();
        String resposta = controllerDisciplina.matricularEstudante(estudanteId, disciplinaID);
        System.out.println(resposta);
    }

    private void listarDisciplinasEstudante() {
        System.out.print("Digite o ID do estudante: ");
        int estudanteId = sc.nextInt();
        String resposta = controllerDisciplina.listarDisciplinasMatriculadas(estudanteId);
        System.out.println(resposta);
    }

    private void removerDisciplinaEstudante() {
        System.out.print("Digite o ID do estudante: ");
        int estudanteId = sc.nextInt();
        System.out.print("Digite o ID da disciplina: ");
        int disciplinaId = sc.nextInt();
        String resposta = controllerDisciplina.removerDisciplina(estudanteId, disciplinaId);
        System.out.println(resposta);
    }

    private void reservarLivro() {
        controllerLivro.mostrarLivros();
        System.out.print("Digite o ID do estudante: ");
        int estudanteId = sc.nextInt();
        System.out.print("Digite o ID do livro: ");
        int livroId = sc.nextInt();
        String resposta = controllerLivro.reservarLivro(estudanteId, livroId);
        System.out.println(resposta);
    }

    private void listarLivrosReservados() {
        System.out.print("Digite o ID do estudante: ");
        int estudanteId = sc.nextInt();
        String resposta = controllerLivro.listarLivrosReservados(estudanteId);
        System.out.println(resposta);
    }

    private void cancelarReservaLivro() {
        System.out.print("Digite o ID do estudante: ");
        int estudanteId = sc.nextInt();
        System.out.print("Digite o ID do livro: ");
        int livroId = sc.nextInt();
        String resposta = controllerLivro.cancelarReserva(estudanteId, livroId);
        System.out.println(resposta);
    }
}