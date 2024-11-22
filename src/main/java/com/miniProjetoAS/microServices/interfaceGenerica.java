package com.miniProjetoAS.microServices;


import java.util.List;

public interface interfaceGenerica<T> {

    List<T> obterInfo (String responseBody);
}
