package com.miniProjetoAS.service;

import com.miniProjetoAS.model.Aluno;
import com.miniProjetoAS.model.Livro;
import com.miniProjetoAS.microServices.InterfaceHttpLivros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BibliotecaService {
    private final InterfaceHttpLivros livroService;
    private final Map<Integer, List<Livro>> reservas;

    public BibliotecaService(InterfaceHttpLivros livroService) {
        this.livroService = livroService;
        this.reservas = new HashMap<>();
    }

    public boolean reservarLivro(Aluno estudante, Livro livro) {
        if ("ativo".equalsIgnoreCase(estudante.isStatus())) {
            reservas.computeIfAbsent(estudante.getId(), k -> new ArrayList<>()).add(livro);
            return true;
        }
        return false;
    }

    public List<Livro> listarLivrosReservados(int estudanteId) {
        return reservas.getOrDefault(estudanteId, new ArrayList<>());
    }

    public boolean cancelarReserva(int estudanteId, int livroId) {
        List<Livro> livrosReservados = reservas.get(estudanteId);
        if (livrosReservados != null) {
            return livrosReservados.removeIf(livro -> livro.getId() == livroId);
        }
        return false;
    }
}