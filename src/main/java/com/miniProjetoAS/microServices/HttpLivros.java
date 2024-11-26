package com.miniProjetoAS.microServices;

import com.miniProjetoAS.model.Livro;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpLivros implements InterfaceHttpLivros {

    private final String endpoint = "https://qiiw8bgxka.execute-api.us-east-2.amazonaws.com/acervo/biblioteca";

    private final HttpClient client = HttpClient.newHttpClient();

    private final parserLivro parser = new parserLivro();


    @Override
    public List<Livro> obterLivros() {
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
