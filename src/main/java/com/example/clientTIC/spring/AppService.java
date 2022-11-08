package com.example.clientTIC.spring;


import com.example.clientTIC.AppUser;
import com.example.clientTIC.AppUserRole;
import com.example.clientTIC.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
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

    public void addNewClub(String nombre, String dir, String email, String password) {
        String json = "";

        try {
            ObjectMapper mapper = new ObjectMapper();
            List<String> list = new ArrayList<>();
            list.add(nombre);
            list.add(dir);
            list.add(email);
            list.add(password);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/club")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();

            if (apiResponse.getStatus() != 200) {
                throw new IllegalStateException("club no creado");
            }
        } catch (UnirestException | JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addNewClubUser(String email, String password, Long clubId) {
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            AppUser appUser = new AppUser(email, password, AppUserRole.CLUB_USER);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(appUser);
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/club/user/" + clubId)
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
        } catch (UnirestException | JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteClubs(String clubName) {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.delete("http://localhost:8080/club/" + clubName).asJson();
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public HttpResponse<JsonNode> cameToActivity(Long cedula, Long activityId) {
        String json = "";
        HttpResponse<JsonNode> apiResponse = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cedula);
            apiResponse = Unirest.post("http://localhost:8080/club/activity/" + activityId)
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
        } catch (UnirestException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return apiResponse;
    }

    public ObservableList<Company> getListOfCompanies() {
        ObjectMapper mapper = new ObjectMapper();
        List<Company> list = null;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/company").asJson();
            list = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return FXCollections.observableList(list);

    }

    public void addNewCompany(String nombre, Long nroAccount,String email, String password) {
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<String> list = new ArrayList<>();
            list.add(nombre);
            list.add(nroAccount.toString());
            list.add(email);
            list.add(password);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/company")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
        } catch (UnirestException | JsonProcessingException ex) {
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
            Admin admin = new Admin(email, password);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(admin);
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/admin")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
        } catch (UnirestException | JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteAdmin(String email) {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.delete("http://localhost:8080/admin/" + email).asJson();
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ObservableList<Employee> getListOfEmployees() {
        ObjectMapper mapper = new ObjectMapper();
        List<Employee> list = null;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/employee").asJson();
            list = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return FXCollections.observableList(list);
    }

    public ObservableList<Employee> getListOfCompanyEmployees(Long companyId) {
        ObjectMapper mapper = new ObjectMapper();
        List<Employee> list = null;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/company/" + companyId).asJson();
            list = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return FXCollections.observableList(list);
    }

    public void addNewEmployee(Long cedula, Company company, Long saldo, String email, String password) {
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            Employee employee = new Employee(company, cedula, saldo, new ArrayList<Activity>(), new AppUser(email, password, AppUserRole.EMPLOYEE));
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee);
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/employee")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
        } catch (UnirestException | JsonProcessingException ex) {
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

    public List<List> getListOfActivities() {
        ObjectMapper mapper = new ObjectMapper();
        List<List> list = null;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/club/activity").asJson();
            list = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<List<List>>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<List> getListOfActivitiesByCategory(String category) {
        ObjectMapper mapper = new ObjectMapper();
        List<List> list = null;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/club/activity/" + category).asJson();
            list = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<List<List>>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<List> getListOfFavs(AppUser appUser) {
        ObjectMapper mapper = new ObjectMapper();
        List<List> list = null;
        Long userId = appUser.getId();
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/employee/favourite/" + userId).asJson();
            list = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<List<List>>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public void addNewActivity(Club club, String nombre, Long precio, List<Quota> cupos, ActivityCategories activityCategories) {
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();

            Activity activity = new Activity(club, nombre, precio, cupos, activityCategories);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(activity);
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/club/activity")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
        } catch (UnirestException | JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public HttpResponse<JsonNode> login(String email, String password) throws UnirestException {
        String json = "";
        HttpResponse<JsonNode> apiResponse = null;

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

    public HttpResponse<JsonNode> makeReservation(AppUser appUser, String fecha, String scheduleId) {
        if (appUser.getAppUserRole().equals(AppUserRole.EMPLOYEE)) {
            String json = "";
            HttpResponse<JsonNode> apiResponse = null;

            try {
                ObjectMapper mapper = new ObjectMapper();
                List<String> list = new ArrayList<>();
                list.add(appUser.getId().toString());
                list.add(scheduleId);
                list.add(fecha);
                json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
                apiResponse = Unirest.post("http://localhost:8080/club/activity/register")
                        .header("Content-Type", "application/json")
                        .body(json).asJson();
            } catch (UnirestException | JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return apiResponse;
        }
        throw new IllegalStateException("usuario no es empleado");
    }

    public HttpResponse<JsonNode> checkInWithReservation(Long cedula, String hora, Long activityId) {

        String json = "";
        HttpResponse<JsonNode> apiResponse = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            List<String> list = new ArrayList<>();
            list.add(activityId.toString());
            list.add(cedula.toString());
            list.add(hora);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
            apiResponse = Unirest.post("http://localhost:8080/club/activity/checkIn")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
            } catch (UnirestException | JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return apiResponse;
        }


    public HttpResponse<JsonNode> checkInWithoutReservation(Long cedula, Long activityId, String hora) {
        String json = "";
        HttpResponse<JsonNode> apiResponse = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<String> list = new ArrayList<>();
            list.add(activityId.toString());
            list.add(cedula.toString());
            list.add(hora);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
            apiResponse = Unirest.put("http://localhost:8080/club/activity/checkIn")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
            } catch (UnirestException | JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return apiResponse;
        }


    public void addFavourite(AppUser appUser, Long activityId) {
        if (appUser.getAppUserRole().equals(AppUserRole.EMPLOYEE)) {
            String json = "";
            HttpResponse<JsonNode> apiResponse = null;

            try {
                ObjectMapper mapper = new ObjectMapper();
                json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(appUser);
                apiResponse = Unirest.post("http://localhost:8080/employee/" + activityId)
                        .header("Content-Type", "application/json")
                        .body(json).asJson();
            } catch (UnirestException | JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void uploadImage() {
        File file = new File("src/main/resources/descarga.jpg");
        FileInputStream input = null;
        MultipartFile multipartFile = null;

        ObjectMapper mapper = new ObjectMapper();
        Imagen modeloFile = null;
        String json = "";
        try {
            input = new FileInputStream(file);
            multipartFile = new MockMultipartFile("file", file.getName(), "image/jpg", IOUtils.toByteArray(input));
            modeloFile = new Imagen(multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getBytes());
            json = mapper.writeValueAsString(modeloFile);
            HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/image")
                    .header("Content-Type", "application/json;charset=utf-8")
                    .body(json)
                    .asJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Image> getActivityImages(Long activityId) {
        ObjectMapper mapper = new ObjectMapper();
        HttpResponse<InputStream> a = null;
        try {
            a = Unirest.get("http://localhost:8080/image/" + activityId).asBinary();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }

        List<byte[]> images = null;
        try {
            images = mapper.readValue(a.getBody(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Image> pictures = new ArrayList<>();

        for (byte[] i : images) {
            ByteArrayInputStream bytearray = new ByteArrayInputStream(i);
            Image imagenverdadera = new Image(bytearray);
            pictures.add(imagenverdadera);
        }
        return pictures;
    }

    public void uploadActivityPicture(File file, Long activityId) {
        FileInputStream input = null;
        MultipartFile multipartFile = null;

        ObjectMapper mapper = new ObjectMapper();
        Imagen modeloFile = null;
        String json = "";
        try {
            input = new FileInputStream(file);
            multipartFile = new MockMultipartFile("file", file.getName(), "image/jpg", IOUtils.toByteArray(input));
            modeloFile = new Imagen(multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getBytes());
            json = mapper.writeValueAsString(modeloFile);
            HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/image/" + activityId)
                    .header("Content-Type", "application/json;charset=utf-8")
                    .body(json)
                    .asJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Quota> getActivityQuota(Long activityId) {
        ObjectMapper mapper = new ObjectMapper();
        List<Quota> activityQuota;
        try {
            HttpResponse apiResponse = Unirest.get("http://localhost:8080/image/" + activityId).asJson();
            activityQuota = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });

        } catch (IOException | UnirestException e) {
            throw new RuntimeException(e);
        }
        return activityQuota;
    }

    public Company appUserGetCompany(Long appUserId){
        ObjectMapper mapper = new ObjectMapper();
        Company company = null;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/login/"+appUserId).asJson();
            company = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return company;
    }

    public Employee appUserGetEmployee(Long appUserId){
        ObjectMapper mapper = new ObjectMapper();
        Employee employee = null;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/login/"+appUserId).asJson();
            employee = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return employee;
    }

    public Admin appUserGetAdmin(Long appUserId){
        ObjectMapper mapper = new ObjectMapper();
        Admin admin = null;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/login/"+appUserId).asJson();
            admin = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return admin;
    }

    public Club appUserGetClub(Long appUserId){
        ObjectMapper mapper = new ObjectMapper();
        Club club = null;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/login/"+appUserId).asJson();
            club = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return club;
    }

    public Activity getActivityByNombre(Long clubId,String nombre){
        ObjectMapper mapper = new ObjectMapper();
        Activity activity = null;
        try {
            List<String> list = new ArrayList<>();
            list.add(clubId.toString());
            list.add(nombre);
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/company/activity/byName").asJson();
            activity = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return activity;
    }

    public Long getCostsForTheMonth(Long companyId,String fechaMesAño){
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        Long cost = null;
        try {
            List<String> list = new ArrayList<>();
            list.add(companyId.toString());
            list.add(fechaMesAño);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/company/costs").header("Content-Type", "application/json").body(json).asJson();
            cost = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return cost;
    }
}





