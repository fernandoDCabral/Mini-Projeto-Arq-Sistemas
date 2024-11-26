package com.miniProjetoAS.microServices;

import com.miniProjetoAS.model.Livro;

import java.util.List;

public interface InterfaceHttpLivros {
    List<Livro> obterLivros();
}
