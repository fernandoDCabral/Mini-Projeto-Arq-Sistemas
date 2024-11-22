package com.miniProjetoAS.microServices;

import com.miniProjetoAS.model.Estudante;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class parserEstudantes implements JsonParser<Estudante> {


    @Override
    public List<Estudante> parse(String responseBody) {
        JSONArray estudantesArray = new JSONArray(responseBody);
        List<Estudante> estudantes = new ArrayList<>();

        for (int i = 0; i < estudantesArray.length(); i++) {
            JSONObject estudanteObj = estudantesArray.getJSONObject(i);
            Estudante estudante = new Estudante(
                    estudanteObj.getInt("id"),
                    estudanteObj.getString("nome"),
                    estudanteObj.getString("curso"),
                    estudanteObj.getString("modalidade"),
                    estudanteObj.getString("status")
            );
            estudantes.add(estudante);
        }
        return estudantes;
    }
}
