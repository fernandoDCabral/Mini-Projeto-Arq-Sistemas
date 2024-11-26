
package com.miniProjetoAS.controller;

import com.miniProjetoAS.model.Aluno;
import com.miniProjetoAS.model.Disciplina;
import com.miniProjetoAS.service.AlunoService;
import com.miniProjetoAS.service.MatriculaService;
import com.miniProjetoAS.service.DisciplinaService;
import com.miniProjetoAS.microServices.HttpDisciplinas;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerMatricula {
    private final MatriculaService matriculaService;
    private final DisciplinaService disciplinaService;
    private final AlunoService estudanteService;

    public ControllerMatricula() {
        this.matriculaService = new MatriculaService(new HttpDisciplinas());
        this.disciplinaService = new DisciplinaService(new HttpDisciplinas());
        this.estudanteService = new AlunoService();
    }

    public String matricularEstudante(int estudanteId, int disciplinaId) {
        Aluno estudante = estudanteService.buscarEstudantePorId(estudanteId);
        if (estudante != null) {
            Disciplina disciplinaEscolhida = disciplinaService.buscarDisciplinaPorId(disciplinaId);

            if (disciplinaEscolhida != null) {
                if (matriculaService.matricularEstudante(estudante, disciplinaEscolhida)) {
                    return "Matrícula realizada com sucesso.";
                } else {
                    return "Não foi possível realizar a matrícula.";
                }
            } else {
                return "Disciplina não encontrada.";
            }
        } else {
            return "Estudante não encontrado.";
        }
    }

    public String listarDisciplinasMatriculadas(int estudanteId) {
        List<Disciplina> disciplinas = matriculaService.listarDisciplinasMatriculadas(estudanteId);
        return disciplinas.isEmpty()
                ? "Nenhuma disciplina encontrada para o estudante."
                : disciplinas.stream().map(Disciplina::toString).collect(Collectors.joining("\n"));
    }

    public String removerDisciplina(int estudanteId, int disciplinaId) {
        boolean sucesso = matriculaService.removerDisciplina(estudanteId, disciplinaId);
        return sucesso ? "Disciplina removida com sucesso." : "Não foi possível remover a disciplina.";
    }

    public void mostrarDisciplinas() {
        List<Disciplina> disciplinas = disciplinaService.listarDisciplinas();
        System.out.println("Disciplinas disponíveis para matrícula:");
        disciplinas.forEach(System.out::println);
    }
}
