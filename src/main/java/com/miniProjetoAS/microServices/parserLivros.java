package com.miniProjetoAS.microServices;

import com.miniProjetoAS.model.Livros;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class parserLivros implements JsonParser<Livros> {

    @Override
    public List<Livros> parse(String responseBody) {
        JSONArray livrosArray = new JSONArray(responseBody);
        List<Livros> livros = new ArrayList<>();

        for (int i = 0; i < livrosArray.length(); i++) {
            JSONObject livroObj = livrosArray.getJSONObject(i);
            Livros livro = new Livros(
                    livroObj.getInt("id"),
                    livroObj.getString("titulo"),
                    livroObj.getString("autor"),
                    livroObj.getInt("ano"),
                    livroObj.getString("status")
            );
            livros.add(livro);
        }
        return livros;
        }
}
