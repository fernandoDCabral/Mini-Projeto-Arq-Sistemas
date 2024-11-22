package com.miniProjetoAS.microServices;

import java.util.List;

public interface JsonParser<T> {

    List<T> parse(String responseBody);

}
