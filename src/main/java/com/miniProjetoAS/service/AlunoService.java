package com.miniProjetoAS.service;

import com.miniProjetoAS.microServices.HttpAlunos;
import com.miniProjetoAS.model.Aluno;

import java.util.List;
import java.util.stream.Collectors;

public class AlunoService {
    private final HttpAlunos estudanteService = new HttpAlunos();

    public List<Aluno> obterEstudantesDeHistoriaPresencial() {
        return estudanteService.obterEstudantes().stream()
                .filter(estudante -> "História".equalsIgnoreCase(estudante.getCurso())
                        && "presencial".equalsIgnoreCase(estudante.getModalidade()))
                .collect(Collectors.toList());
    }

    public Aluno buscarEstudantePorId(int id) {
        return estudanteService.obterEstudantes().stream()
                .filter(estudante -> estudante.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Aluno> buscarEstudantePorNome(String nome) {
        return estudanteService.obterEstudantes().stream()
                .filter(estudante -> estudante.getNome().equalsIgnoreCase(nome))
                .toList();
    }

}
