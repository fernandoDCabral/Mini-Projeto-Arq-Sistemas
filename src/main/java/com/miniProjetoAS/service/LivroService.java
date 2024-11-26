package com.miniProjetoAS.service;

import com.miniProjetoAS.model.Livro;
import com.miniProjetoAS.microServices.InterfaceHttpLivros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LivroService {
    private final Map<Integer, Livro> livrosMap = new HashMap<>();

    // Construtor que recebe o serviço de livros (InterfaceHttpLivros) e carrega os livros no mapa
    public LivroService(InterfaceHttpLivros servicoLivros) {
        // Carrega todos os livros no mapa local na inicialização
        List<Livro> livros = servicoLivros.obterLivros();
        for (Livro livro : livros) {
            livrosMap.put(livro.getId(), livro);
        }
    }

    // Buscar livro pelo ID
    public Livro buscarLivroPorId(int livroId) {
        return livrosMap.get(livroId); // Retorna o livro pelo ID
    }

    // Listar todos os livros disponíveis
    public List<Livro> listarLivros() {
        return new ArrayList<>(livrosMap.values()); // Retorna todos os livros em formato de lista
    }
}