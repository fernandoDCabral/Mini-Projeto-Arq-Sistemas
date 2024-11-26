package com.miniProjetoAS.controller;

import com.miniProjetoAS.service.AlunoService;
import com.miniProjetoAS.model.Aluno;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerAluno {
    private final AlunoService estudanteService = new AlunoService();

    public String listarEstudantesDeHistoriaPresencial() {
        List<Aluno> estudantes = estudanteService.obterEstudantesDeHistoriaPresencial();
        if (estudantes.isEmpty()) {
            return "Nenhum estudante encontrado.";
        }else{
            return estudantes.stream()
                    .map(Aluno::toString)
                    .collect(Collectors.joining("\n"));
        }

    }

    public String buscarEstudantePorId(int id) {
        Aluno estudante = estudanteService.buscarEstudantePorId(id);
        return estudante != null ? estudante.toString() : "Estudante não encontrado.";
    }

    public String buscarEstudantePorNome(String nome) {
        List<Aluno> estudantes = estudanteService.buscarEstudantePorNome(nome);
        if (estudantes.isEmpty()){
            return "Nenhum estudante encontrado.";
        }else{
            return estudantes.stream().map(Aluno::toString).collect(Collectors.joining("\n"));
        }
    }
}
