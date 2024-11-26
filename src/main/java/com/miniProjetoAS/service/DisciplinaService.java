package com.miniProjetoAS.service;

import com.miniProjetoAS.model.Disciplina;
import com.miniProjetoAS.microServices.HttpDisciplinas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class DisciplinaService {
    private final Map<Integer, Disciplina> disciplinasMap = new HashMap<>();

    public DisciplinaService(HttpDisciplinas httpDisciplinas) {
        List<Disciplina> disciplinas = httpDisciplinas.obterDisciplinas();
        for (Disciplina disciplina : disciplinas) {
            disciplinasMap.put(disciplina.getId(), disciplina);
        }
    }

    public Disciplina buscarDisciplinaPorId(int disciplinaId) {
        return disciplinasMap.get(disciplinaId);
    }

    public List<Disciplina> listarDisciplinas() {
        return new ArrayList<>(disciplinasMap.values());
    }
}

