package com.miniProjetoAS.microServices;

import com.miniProjetoAS.model.Aluno;

import java.util.List;

public interface InterfaceHttpAlunos {
    List<Aluno> obterEstudantes();
}