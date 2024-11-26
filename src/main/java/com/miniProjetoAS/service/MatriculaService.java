package com.miniProjetoAS.service;

import com.miniProjetoAS.model.Aluno;
import com.miniProjetoAS.model.Disciplina;
import com.miniProjetoAS.microServices.InterfaceHttpDisciplinas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatriculaService {
    private final InterfaceHttpDisciplinas httpDisciplinas;
    private final Map<Integer, List<Disciplina>> matriculas;

    public MatriculaService(InterfaceHttpDisciplinas httpDisciplinas) {
        this.httpDisciplinas = httpDisciplinas;
        this.matriculas = new HashMap<>();
    }

    public boolean matricularEstudante(Aluno estudante, Disciplina disciplina) {
        if ("ativo".equalsIgnoreCase(estudante.isStatus()) &&
                "presencial".equalsIgnoreCase(estudante.getModalidade()) &&
                "História".equalsIgnoreCase(estudante.getCurso()) &&
                "História".equalsIgnoreCase(disciplina.getCurso())) {

            matriculas.computeIfAbsent(estudante.getId(), k -> new ArrayList<>()).add(disciplina);
            return true;
        }
        return false;
    }

    public List<Disciplina> listarDisciplinasMatriculadas(int estudanteId) {
        return matriculas.getOrDefault(estudanteId, new ArrayList<>());
    }

    public boolean removerDisciplina(int estudanteId, int disciplinaId) {
        List<Disciplina> disciplinasMatriculadas = matriculas.get(estudanteId);
        if (disciplinasMatriculadas != null) {
            return disciplinasMatriculadas.removeIf(disciplina -> disciplina.getId() == disciplinaId);
        }
        return false;
    }

}
