package com.miniProjetoAS.microServices;

import com.miniProjetoAS.model.Aluno;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class parserAluno implements JsonParser<Aluno> {

    @Override
    public List<Aluno> parse(String responseBody) {
        JSONArray estudantesArray = new JSONArray(responseBody);
        List<Aluno> estudantes = new ArrayList<>();

        for (int i = 0; i < estudantesArray.length(); i++) {
            JSONObject estudanteObj = estudantesArray.getJSONObject(i);
            Aluno estudante = new Aluno(
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
