package com.clapter.httpautomator.http;

import com.clapter.httpautomator.http.api.IHttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class HttpClientImpl implements IHttpClient {

    @Override
    public String sendPost(String url, String parameters) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            //String requestBody = "{\"key1\":\"value1\", \"key2\":\"value2\"}";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(parameters, StandardCharsets.UTF_8))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode()+" "+response.body());
            return "";
        }catch (URISyntaxException | IOException | InterruptedException e) {

        }
        return "";
    }

}
