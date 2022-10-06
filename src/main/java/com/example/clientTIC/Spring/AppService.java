package com.example.clientTIC.Spring;


import com.example.clientTIC.UI.Company;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@org.springframework.stereotype.Service
public class AppService {

    public void getListOfClubs() {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/club").asJson();
            String list = String.valueOf(apiResponse.getBody()); //lista que debemos mostrar
            System.out.println(list);
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addNewClub(String nombre, String tel, String dir) {
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode club = mapper.createObjectNode();
            club.put("nombre", nombre);
            club.put("tel", tel);
            club.put("dir", dir);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(club);
        } catch (Exception ignored) {
        }
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/club")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteClubs(String club) {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.delete("http://localhost:8080/club/" + club).asJson();
            String list = String.valueOf(apiResponse.getBody()); //lista que debemos mostrar
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ObservableList<Company> getListOfCompanies() {
        ObjectMapper mapper = new ObjectMapper();
        List<Company> list = null;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/company").asJson();
            list = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<List<Company>>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return FXCollections.observableList(list);

    }

    public void addNewCompany(String name, String email, String nroAccount, String password) {
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode club = mapper.createObjectNode();
            club.put("nombre", name);
            club.put("mail", email);
            club.put("nroCuenta", nroAccount);
            club.put("password", password);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(club);
        } catch (Exception ignored) {
        }
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/company")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addNewAdmin(String email, String password) {
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode admin = mapper.createObjectNode();
            admin.put("mail", email);
            admin.put("password", password);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(admin);
        } catch (Exception ignored) {
        }
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/admin")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteCompanies(String companyName) {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.delete("http://localhost:8080/company/" + companyName).asJson();
            String list = String.valueOf(apiResponse.getBody()); //lista que debemos mostrar
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void getListOfEmployees() {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/employee").asJson();
            String list = String.valueOf(apiResponse.getBody()); //lista que debemos mostrar
            System.out.println(list);
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addNewEmployee(String cedula, String email, String saldo) {
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode employee = mapper.createObjectNode();
            employee.put("cedula", cedula);
            employee.put("email", email);
            employee.put("saldo", saldo);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee);
        } catch (Exception ignored) {
        }
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/employee")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteEmployees(String cedula) {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.delete("http://localhost:8080/employee/" + cedula).asJson();
            String list = String.valueOf(apiResponse.getBody()); //lista que debemos mostrar

        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public HttpResponse<JsonNode> login(String mail, String password) throws UnirestException {
        String json = "";
        HttpResponse<JsonNode> apiResponse=null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode user = mapper.createObjectNode();
            user.put("mail", mail);
            user.put("password", password);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        } catch (Exception ignored) {
        }
        try {
            apiResponse = Unirest.post("http://localhost:8080/login")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        return apiResponse;
    }
}
