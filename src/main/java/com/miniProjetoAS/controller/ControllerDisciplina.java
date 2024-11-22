package com.miniProjetoAS.controller;

import com.miniProjetoAS.service.EstudanteService;
import com.miniProjetoAS.model.Estudante;
import com.miniProjetoAS.service.DisciplinaService;
import com.miniProjetoAS.model.Disciplina;

import com.miniProjetoAS.microServices.servicoDisciplinas;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ControllerDisciplina {
    private final DisciplinaService DisciplinaService;
    private final Scanner sc = new Scanner(System.in);
    private final EstudanteService estudanteService = new EstudanteService();
    private final servicoDisciplinas servicoDisciplinas = new servicoDisciplinas();

    public ControllerDisciplina(DisciplinaService matriculaService) {
        this.DisciplinaService = matriculaService;
    }

    public String matricularEstudante(int estudanteId, int disciplinaId) {
        Estudante ID = estudanteService.buscarEstudantePorId(estudanteId);
        if (ID != null) {
            List<Disciplina> disciplinas = servicoDisciplinas.obterDisciplinas();
            int DisciplinaId = disciplinaId;
            Disciplina disciplinaEscolhida = disciplinas.stream()
                    .filter(d -> d.getId() == disciplinaId)
                    .findFirst()
                    .orElse(null);

            if (disciplinaEscolhida != null && DisciplinaService.matricularEstudante(ID, disciplinaEscolhida)) {
                return "Matrícula realizada com sucesso.";
            } else {
                return "Não foi possível realizar a matrícula.";
            }
        } else {
            return "Estudante não encontrado.";
        }
    }

    public String listarDisciplinasMatriculadas(int estudanteId) {
        List<Disciplina> disciplinas = DisciplinaService.listarDisciplinasMatriculadas(estudanteId);
        return disciplinas.isEmpty()
                ? "Nenhuma disciplina encontrada para o estudante."
                : disciplinas.stream().map(Disciplina::toString).collect(Collectors.joining("\n"));
    }

    public String removerDisciplina(int estudanteId, int disciplinaId) {
        boolean sucesso = DisciplinaService.removerDisciplina(estudanteId, disciplinaId);
        return sucesso ? "Disciplina removida com sucesso." : "Não foi possível remover a disciplina.";
    }
}
