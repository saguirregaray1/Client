package com.example.clientTIC.Spring;


import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


@org.springframework.stereotype.Service
public class Service {

    public void getListOfClubs(){
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/club").asJson();
            String list= String.valueOf(apiResponse.getBody()); //lista que debemos mostrar
            System.out.println(list);
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addNewClub(String nombre,String tel,String dir) {
        String json="";
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode club = mapper.createObjectNode();
            club.put("nombre",nombre);
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
    public void deleteClubs(String club){
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.delete("http://localhost:8080/club/"+club).asJson();
            String list= String.valueOf(apiResponse.getBody()); //lista que debemos mostrar
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void getListOfCompanies(){
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/company").asJson();
            String list= String.valueOf(apiResponse.getBody()); //lista que debemos mostrar
            System.out.println(list);
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addNewCompany(String nombre,String mail,String nroCuenta) {
        String json="";
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode company = mapper.createObjectNode();
            company.put("nombre",nombre);
            company.put("mail", mail);
            company.put("nroCuenta", nroCuenta);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(company);
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

    public void deleteCompanies(String company){
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.delete("http://localhost:8080/company/"+company).asJson();
            String list= String.valueOf(apiResponse.getBody()); //lista que debemos mostrar
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void getListOfEmployees(){
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/employee").asJson();
            String list= String.valueOf(apiResponse.getBody()); //lista que debemos mostrar
            System.out.println(list);
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addNewEmployee(String cedula,String email,String saldo) {
        String json="";
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode employee = mapper.createObjectNode();
            employee.put("cedula",cedula);
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

    public void deleteEmployees(String cedula){
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.delete("http://localhost:8080/employee/"+cedula).asJson();
            String list= String.valueOf(apiResponse.getBody()); //lista que debemos mostrar

        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }
}
