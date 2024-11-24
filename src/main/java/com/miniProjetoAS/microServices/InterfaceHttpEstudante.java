package com.miniProjetoAS.microServices;

import com.miniProjetoAS.model.Estudante;

import java.util.List;

public interface InterfaceHttpEstudante {
    List<Estudante> obterEstudantes();
}