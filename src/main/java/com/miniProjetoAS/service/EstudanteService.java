package com.miniProjetoAS.service;

import com.miniProjetoAS.microServices.servicoEstudante;
import com.miniProjetoAS.model.Estudante;

import java.util.List;
import java.util.stream.Collectors;

public class EstudanteService {
    private final servicoEstudante estudanteService = new servicoEstudante();

    public List<Estudante> obterEstudantesDeHistoriaPresencial() {
        return estudanteService.obterEstudantes().stream()
                .filter(estudante -> "História".equalsIgnoreCase(estudante.getCurso())
                        && "presencial".equalsIgnoreCase(estudante.getModalidade()))
                .collect(Collectors.toList());
    }

    public Estudante buscarEstudantePorId(int id) {
        return estudanteService.obterEstudantes().stream()
                .filter(estudante -> estudante.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Estudante> buscarEstudantePorNome(String nome) {
        return estudanteService.obterEstudantes().stream()
                .filter(estudante -> estudante.getNome().equalsIgnoreCase(nome))
                .toList();
    }

}
