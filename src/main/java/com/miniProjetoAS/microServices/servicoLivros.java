package com.miniProjetoAS.microServices;

import com.miniProjetoAS.model.Livros;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class servicoLivros implements interfaceLivros {

    private final String endpoint = "https://qiiw8bgxka.execute-api.us-east-2.amazonaws.com/acervo/biblioteca";

    private final HttpClient client = HttpClient.newHttpClient();

    private final parserLivros parser = new parserLivros();


    @Override
    public List<Livros> obterLivros() {
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
