package com.miniProjetoAS.controller;

import com.miniProjetoAS.service.EstudanteService;
import com.miniProjetoAS.model.Estudante;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerEstudante {
    private final EstudanteService estudanteService = new EstudanteService();

    public String listarEstudantesDeHistoriaPresencial() {
        List<Estudante> estudantes = estudanteService.obterEstudantesDeHistoriaPresencial();
        if (estudantes.isEmpty()) {
            return "Nenhum estudante encontrado.";
        }else{
            return estudantes.stream()
                    .map(Estudante::toString)
                    .collect(Collectors.joining("\n"));
        }

    }

    public String buscarEstudantePorId(int id) {
        Estudante estudante = estudanteService.buscarEstudantePorId(id);
        return estudante != null ? estudante.toString() : "Estudante não encontrado.";
    }

    public String buscarEstudantePorNome(String nome) {
        List<Estudante> estudantes = estudanteService.buscarEstudantePorNome(nome);
        if (estudantes.isEmpty()){
            return "Nenhum estudante encontrado.";
        }else{
            return estudantes.stream().map(Estudante::toString).collect(Collectors.joining("\n"));
        }
    }
}
