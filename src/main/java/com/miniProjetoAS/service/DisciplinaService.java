package com.miniProjetoAS.service;

import com.miniProjetoAS.model.Disciplina;
import com.miniProjetoAS.microServices.HttpDisciplinas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class DisciplinaService {
    private final Map<Integer, Disciplina> disciplinasMap = new HashMap<>();

    public DisciplinaService(HttpDisciplinas servicoDisciplinas) {
        // Carrega todas as disciplinas no mapa local na inicialização
        List<Disciplina> disciplinas = servicoDisciplinas.obterDisciplinas();
        for (Disciplina disciplina : disciplinas) {
            disciplinasMap.put(disciplina.getId(), disciplina);
        }
    }

    // Buscar disciplina pelo ID
    public Disciplina buscarDisciplinaPorId(int disciplinaId) {
        return disciplinasMap.get(disciplinaId); // Retorna a disciplina pelo ID
    }

    // Listar todas as disciplinas
    public List<Disciplina> listarDisciplinas() {
        return new ArrayList<>(disciplinasMap.values()); // Retorna todas as disciplinas
    }
}

