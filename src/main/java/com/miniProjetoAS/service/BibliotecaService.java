package com.miniProjetoAS.service;

import com.miniProjetoAS.model.Aluno;
import com.miniProjetoAS.model.Livro;
import com.miniProjetoAS.microServices.InterfaceHttpLivros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BibliotecaService {
    private final InterfaceHttpLivros httpLivros;
    private final Map<Integer, List<Livro>> reservas;

    public BibliotecaService(InterfaceHttpLivros httpLivros) {
        this.httpLivros = httpLivros;
        this.reservas = new HashMap<>();
    }

    public boolean reservarLivro(Aluno estudante, Livro livro) {
        if (livro.isReservado()) {
            return false;
        }
        if (estudante != null && "ativo".equalsIgnoreCase(estudante.isStatus())) {
            if (!reservas.containsKey(estudante.getId())) {
                reservas.put(estudante.getId(), new ArrayList<>());
            }
            reservas.get(estudante.getId()).add(livro);
            livro.setReservado(true);
            return true;
        }else{
            return false;
        }

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
