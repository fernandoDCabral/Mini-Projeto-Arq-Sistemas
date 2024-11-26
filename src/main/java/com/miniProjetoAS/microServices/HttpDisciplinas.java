package com.miniProjetoAS.microServices;

import com.miniProjetoAS.model.Disciplina;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpDisciplinas implements InterfaceHttpDisciplinas {

    private final String endpoint = "https://sswfuybfs8.execute-api.us-east-2.amazonaws.com/disciplinaServico/msDisciplina";

    private final HttpClient client = HttpClient.newHttpClient();

    private final parserDisciplina parser = new parserDisciplina();


    @Override
    public List<Disciplina> obterDisciplinas() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return parser.parse(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}