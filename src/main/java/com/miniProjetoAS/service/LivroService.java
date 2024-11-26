package com.miniProjetoAS.service;

import com.miniProjetoAS.model.Livro;
import com.miniProjetoAS.microServices.InterfaceHttpLivros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LivroService {
    private final Map<Integer, Livro> livrosMap = new HashMap<>();

    public LivroService(InterfaceHttpLivros httpLivros) {
        List<Livro> livros = httpLivros.obterLivros();
        for (Livro livro : livros) {
            livrosMap.put(livro.getId(), livro);
        }
    }

    public Livro buscarLivroPorId(int livroId) {
        return livrosMap.get(livroId);
    }

    public List<Livro> listarLivros() {
        return new ArrayList<>(livrosMap.values());
    }
}