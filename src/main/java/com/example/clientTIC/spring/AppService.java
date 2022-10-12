package com.example.clientTIC.spring;


import com.example.clientTIC.AppUser;
import com.example.clientTIC.AppUserRole;
import com.example.clientTIC.models.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class AppService {

    public ObservableList<Club> getListOfClubs() {
        ObjectMapper mapper = new ObjectMapper();
        List<Club> list = null;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/club").asJson();
            list = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<List<Club>>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return FXCollections.observableList(list);

    }

    public void addNewClub(String nombre,String dir) {
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode club = mapper.createObjectNode();
            club.put("nombre", nombre);
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

    public void deleteClubs(String clubName) {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.delete("http://localhost:8080/company/" + clubName).asJson();
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

    public void addNewCompany(String name, String nroAccount) {
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode club = mapper.createObjectNode();
            club.put("nombre", name);
            club.put("nroCuenta", nroAccount);
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

    public void deleteCompanies(String companyName) {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.delete("http://localhost:8080/company/" + companyName).asJson();
            String list = String.valueOf(apiResponse.getBody()); //lista que debemos mostrar
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ObservableList<AppUser> getListOfAdmins() {
        ObjectMapper mapper = new ObjectMapper();
        List<AppUser> list = null;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/admin").asJson();
            list = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<List<AppUser>>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return FXCollections.observableList(list);
    }
    public void addNewAdmin(String email, String password) {
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            AppUser admin = new AppUser(email,password, AppUserRole.ADMIN);
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

    public void deleteAdmin(String email) {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.delete("http://localhost:8080/employee/" + email).asJson();
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ObservableList<Employee> getListOfEmployees() {
        ObjectMapper mapper = new ObjectMapper();
        List<Employee> list = null;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/admin").asJson();
            list = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<List<Employee>>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return FXCollections.observableList(list);
    }

    public void addNewEmployee(String cedula, Long companyId, String saldo) {
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode employee = mapper.createObjectNode();
            employee.put("companyId",companyId);
            employee.put("cedula", cedula);
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
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ObservableList<List> getListOfActivities(){
        ObjectMapper mapper = new ObjectMapper();
        List<List> list = null;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/club/activity").asJson();
            list = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<List<List>>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return FXCollections.observableList(list);
    }

    public void addNewActivity(Long clubId, String nombre, Long precio, int cupos, ActivityCategories activityCategories){
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            Activity activity = new Activity(clubId,nombre,precio,cupos,activityCategories);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(activity);
        } catch (Exception ignored) {
        }
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/club/activity")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public HttpResponse<JsonNode> login(String email, String password) throws UnirestException {
        String json = "";
        HttpResponse<JsonNode> apiResponse=null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode user = mapper.createObjectNode();
            user.put("email", email);
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
