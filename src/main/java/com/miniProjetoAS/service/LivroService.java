package com.miniProjetoAS.service;

import com.miniProjetoAS.model.Estudante;
import com.miniProjetoAS.model.Livros;
import com.miniProjetoAS.microServices.interfaceLivros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LivroService {
    private final interfaceLivros livroService;
    private final Map<Integer, List<Livros>> reservas;

    public LivroService(interfaceLivros livroService) {
        this.livroService = livroService;
        this.reservas = new HashMap<>();
    }

    //mandar para o estudante
    public boolean reservarLivro(Estudante estudante, Livros livro) {
        if ("ativo".equalsIgnoreCase(estudante.isStatus())) {
            reservas.computeIfAbsent(estudante.getId(), k -> new ArrayList<>()).add(livro);
            return true;
        }
        return false;
    }

    public List<Livros> listarLivrosReservados(int estudanteId) {
        return reservas.getOrDefault(estudanteId, new ArrayList<>());
    }

    public boolean cancelarReserva(int estudanteId, int livroId) {
        List<Livros> livrosReservados = reservas.get(estudanteId);
        if (livrosReservados != null) {
            return livrosReservados.removeIf(livro -> livro.getId() == livroId);
        }
        return false;
    }
}
