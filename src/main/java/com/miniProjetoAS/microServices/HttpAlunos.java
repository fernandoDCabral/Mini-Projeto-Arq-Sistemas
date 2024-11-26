package com.miniProjetoAS.microServices;

import com.miniProjetoAS.model.Aluno;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpAlunos implements InterfaceHttpAlunos {

    private final String endpoint = "https://rmi6vdpsq8.execute-api.us-east-2.amazonaws.com/msAluno";
    private final HttpClient client = HttpClient.newHttpClient();
    private final parserAluno parser = new parserAluno();


    @Override
    public List<Aluno> obterEstudantes() {
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
