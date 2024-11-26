package com.miniProjetoAS.microServices;

import com.miniProjetoAS.model.Disciplina;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class parserDisciplina implements JsonParser<Disciplina> {

    @Override
    public List<Disciplina> parse(String responseBody) {
        JSONArray disciplinasArray = new JSONArray(responseBody);
        List<Disciplina> disciplinas = new ArrayList<>();

        for (int i = 0; i < disciplinasArray.length(); i++) {
            JSONObject disciplinaObj = disciplinasArray.getJSONObject(i);
            Disciplina disciplina = new Disciplina(
                    disciplinaObj.getInt("id"),
                    disciplinaObj.getString("curso"),
                    disciplinaObj.getString("nome")
            );
            disciplinas.add(disciplina);
        }
        return disciplinas;
    }
}
