package com.graphql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class PokemonClient {

    private final String[] headers = {
            "Accept", "application/json",
            "User-Agent", "Java SDK"
    };

    private final HttpClient http = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public List<PokemonDocument> sendAsync(List<URI> uris) {
        return uris.stream()
                .map(this::fetch)
                .map(CompletableFuture::join)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private CompletableFuture<PokemonDocument> fetch(URI uri) {
        var request = HttpRequest.newBuilder(uri)
                .headers(this.headers)
                .timeout(Duration.ofSeconds(20))
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();
        return http.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(this::readValue);
    }

    private PokemonDocument readValue(String body) {
        try {
            return this.mapper.readValue(body, PokemonDocument.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
