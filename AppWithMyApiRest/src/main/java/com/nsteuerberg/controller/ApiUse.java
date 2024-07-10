package com.nsteuerberg.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsteuerberg.model.Users;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.List;

public class ApiUse {
    private static final String BASE_URL = "http://localhost:8080/usuarios/";

    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public void getUsers(){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "all"))
                    .GET()
                    .build();
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            List<Users> usuarios = MAPPER.readValue(response.body(), new TypeReference<List<Users>>(){});
            for (Users e: usuarios){
                System.out.println(e.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void postUsers(Users user){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "save"))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(parseUserToJson(user)))
                .build();
        HttpResponse<String> response = null;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private String parseUserToJson(Users user){
        try {
            return MAPPER.writeValueAsString(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}