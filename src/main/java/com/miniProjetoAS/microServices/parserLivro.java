package com.miniProjetoAS.microServices;

import com.miniProjetoAS.model.Livro;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class parserLivro implements JsonParser<Livro> {

    @Override
    public List<Livro> parse(String responseBody) {
        JSONArray livrosArray = new JSONArray(responseBody);
        List<Livro> livros = new ArrayList<>();

        for (int i = 0; i < livrosArray.length(); i++) {
            JSONObject livroObj = livrosArray.getJSONObject(i);
            Livro livro = new Livro(
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
