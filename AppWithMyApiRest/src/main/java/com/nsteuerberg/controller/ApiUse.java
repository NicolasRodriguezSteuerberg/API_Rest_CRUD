package com.nsteuerberg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsteuerberg.model.Users;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.List;

/**
 * Clase que se encarga de consumir la API
 */
public class ApiUse {
    private static final String BASE_URL = "http://localhost:8080/usuarios/";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Obtiene todos los usuarios de la API
     */
    public void getUsers(){
        try {
            // creamos la petición
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "all"))
                    .GET()
                    .build();
            // enviamos la petición y obtenemos la respuesta
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            // parseamos el json a un objeto User
            List<Users> usuarios = parseJsonToUserList(response.body());
            for (Users e: usuarios){
                System.out.println(e.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Envia un objeto User a la API
     * @param user User: objeto User
     */
    public void postUsers(Users user){
        /*
         * Creamos la petición
         * uri: la url a la que vamos a enviar la petición
         * header: el tipo de contenido que vamos a enviar, existen los siguientes tipos: application/json, application/xml, text/plain, text/html
         */
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "save"))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(parseUserToJson(user)))
                .build();
        // enviamos la petición y obtenemos la respuesta
        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            // parseamos el json a un objeto User y lo imprimimos
            System.out.println(parseJsonToUser(response.body()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    public void update(Users user){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "update/" + user.getId()))
                .header("Content-Type", "application/json")
                .PUT(BodyPublishers.ofString(parseUserToJson(user)))
                .build();
        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body() + "\nAAAAAAAAAAAAAA");
            if (response.body()!= null && !response.body().isEmpty()){
                System.out.println(parseJsonToUser(response.body()));
            } else {
                System.out.println("No se ha podido actualizar el usuario");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    */
    public void update(Users user) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "update/" + user.getId()))
                .header("Content-Type", "application/json")
                .PUT(BodyPublishers.ofString(parseUserToJson(user)))
                .build();
        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == HttpStatus.OK.value()) {
                System.out.println(" Usuario modificado: " + parseJsonToUser(response.body()));
            } else if (response.statusCode() == HttpStatus.NOT_FOUND.value()) {
                System.out.println("Usuario no encontrado: "+ response.body() + " -> " + response.statusCode());
            } else {
                System.out.println("Error al actualizar el usuario: "+ response.body() + " -> " + response.statusCode());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(long id){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "delete/" + id))
                .DELETE()
                .build();
        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == HttpStatus.OK.value())
                System.out.println(response.body());
            else if (response.statusCode() == HttpStatus.NOT_FOUND.value())
                System.out.println("Usuario no encontrado: "+ response.body() + " -> " + response.statusCode());
            else
                System.out.println("Error al eliminar el usuario: "+ response.body() + " -> " + response.statusCode());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convierte un objeto User a un JSON
     * @param user User: objeto User
     * @return String: JSON
     */
    private String parseUserToJson(Users user){
        try {
            return MAPPER.writeValueAsString(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convierte un JSON a un objeto User
     * @param json String: JSON
     * @return User: objeto User
     */
    private Users parseJsonToUser(String json){
        try {
            return MAPPER.readValue(json, Users.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convierte un JSON a una lista de objetos User
     * @param json String: JSON
     * @return List<User>: lista de objetos User
     */
    private List<Users> parseJsonToUserList(String json){
        try {
            return MAPPER.readValue(json, new TypeReference<List<Users>>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}