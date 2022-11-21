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
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;


@Service
public class AppService {

    public ObservableList<Club> getListOfClubs() {
        ObjectMapper mapper = new ObjectMapper();
        List<Club> list;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/club").asJson();
            list = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return FXCollections.observableList(list);
    }

    public void addNewClub(String nombre, String dir, String email, String password) {
        String json;

        try {
            ObjectMapper mapper = new ObjectMapper();
            List<String> list = new ArrayList<>();
            list.add(nombre);
            list.add(dir);
            list.add(email);
            list.add(password);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);

            Unirest.post("http://localhost:8080/club")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
        } catch (UnirestException | JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public HttpResponse<JsonNode> addNewClubUser(String email, String password, Long clubId) {
        String json;
        try {
            ObjectMapper mapper = new ObjectMapper();
            AppUser appUser = new AppUser(email, password, AppUserRole.CLUB_USER);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(appUser);
            return Unirest.post("http://localhost:8080/club/user/" + clubId)
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
        } catch (UnirestException | JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteClubs(String clubName) {
        try {
            Unirest.delete("http://localhost:8080/club/" + clubName).asJson();
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }


    public ObservableList<Company> getListOfCompanies() {
        ObjectMapper mapper = new ObjectMapper();
        List<Company> list;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/company").asJson();
            list = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return FXCollections.observableList(list);

    }


    public void addNewCompany(String nombre, Long nroAccount, String email, String password) {
        String json;
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<String> list = new ArrayList<>();
            list.add(nombre);
            list.add(nroAccount.toString());
            list.add(email);
            list.add(password);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
            Unirest.post("http://localhost:8080/company")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
        } catch (UnirestException | JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteCompanies(String companyName) {
        try {
            Unirest.delete("http://localhost:8080/company/" + companyName).asJson();
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ObservableList<AppUser> getListOfAdmins() {
        ObjectMapper mapper = new ObjectMapper();
        List<AppUser> list;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/admin").asJson();
            list = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return FXCollections.observableList(list);
    }

    public void addNewAdmin(String email, String password) {
        String json;
        try {
            ObjectMapper mapper = new ObjectMapper();
            Admin admin = new Admin(email, password);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(admin);
            Unirest.post("http://localhost:8080/admin")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
        } catch (UnirestException | JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteAdmin(String email) {
        try {
            Unirest.delete("http://localhost:8080/admin/" + email).asJson();
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ObservableList<Employee> getListOfEmployees() {
        ObjectMapper mapper = new ObjectMapper();
        List<Employee> list;
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
        List<Employee> list;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/company/" + companyId).asJson();
            list = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return FXCollections.observableList(list);
    }

    public HttpResponse<JsonNode> addNewEmployee(Long cedula, Company company, Long saldo, String email, String password) {
        String json;
        try {
            ObjectMapper mapper = new ObjectMapper();
            Employee employee = new Employee(company, cedula, saldo, new ArrayList<>(), new AppUser(email, password, AppUserRole.EMPLOYEE));
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee);
            return Unirest.post("http://localhost:8080/employee")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
        } catch (UnirestException | JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteEmployees(String cedula) {
        try {
            Unirest.delete("http://localhost:8080/employee/" + cedula).asJson();
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<List> getListOfActivities() {
        ObjectMapper mapper = new ObjectMapper();
        List<List> list;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/club/activity").asJson();
            list = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<List> getListOfActivitiesByCategory(String category) {
        ObjectMapper mapper = new ObjectMapper();
        List<List> list;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/club/activity/" + category).asJson();
            list = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<List> getListOfActivitiesByClub(Long clubId) {
        ObjectMapper mapper = new ObjectMapper();
        List<List> list;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/club/" + clubId).asJson();
            list = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<List> getListOfFavs(AppUser appUser) {
        ObjectMapper mapper = new ObjectMapper();
        List<List> list;
        Long userId = appUser.getId();
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/employee/favourite/" + userId).asJson();
            list = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public HttpResponse<JsonNode> addNewActivity(Club club, String nombre, Long precio, List<Quota> cupos, ActivityCategories activityCategories) {
        String json;
        try {
            ObjectMapper mapper = new ObjectMapper();

            Activity activity = new Activity(club, nombre, precio, cupos, activityCategories);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(activity);
            return Unirest.post("http://localhost:8080/club/activity")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
        } catch (UnirestException | JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public HttpResponse<JsonNode> login(String email, String password) {
        String json = "";
        HttpResponse<JsonNode> apiResponse;

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
        String json;
        HttpResponse<JsonNode> apiResponse;

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


    public HttpResponse<JsonNode> checkInWithReservation(Long cedula, String hora, Long activityId) {
        String json;
        HttpResponse<JsonNode> apiResponse;

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


    public void addFavourite(AppUser appUser, Long activityId) {
        if (appUser.getAppUserRole().equals(AppUserRole.EMPLOYEE)) {
            String json;
            try {
                ObjectMapper mapper = new ObjectMapper();
                json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(appUser);
                Unirest.post("http://localhost:8080/employee/" + activityId)
                        .header("Content-Type", "application/json")
                        .body(json).asJson();
            } catch (UnirestException | JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<Image> getActivityImages(Long activityId) {
        ObjectMapper mapper = new ObjectMapper();
        HttpResponse<InputStream> a;
        try {
            a = Unirest.get("http://localhost:8080/image/" + activityId).asBinary();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        if (a.getStatus()!=200){
            return new ArrayList<>();
        }
        List<byte[]> images;
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
        FileInputStream input;
        MultipartFile multipartFile;

        ObjectMapper mapper = new ObjectMapper();
        Imagen modeloFile;
        String json;
        try {
            input = new FileInputStream(file);
            multipartFile = new MockMultipartFile("file", file.getName(), "image/jpg", IOUtils.toByteArray(input));
            modeloFile = new Imagen(multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getBytes());
            json = mapper.writeValueAsString(modeloFile);
            Unirest.post("http://localhost:8080/image/" + activityId)
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
            HttpResponse apiResponse = Unirest.get("http://localhost:8080/club/activity/quota/" + activityId).asJson();
            activityQuota = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });

        } catch (IOException | UnirestException e) {
            throw new RuntimeException(e);
        }
        return activityQuota;
    }

    public Company appUserGetCompany(Long appUserId) {
        ObjectMapper mapper = new ObjectMapper();
        Company company;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/login/" + appUserId).asJson();
            company = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return company;
    }

    public Employee appUserGetEmployee(Long appUserId) {
        ObjectMapper mapper = new ObjectMapper();
        Employee employee;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/login/" + appUserId).asJson();
            employee = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return employee;
    }


    public Club appUserGetClub(Long appUserId) {
        ObjectMapper mapper = new ObjectMapper();
        Club club;
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/login/" + appUserId).asJson();
            club = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return club;
    }



    public Activity getActivityByNombre(Long clubId, String nombre) {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        Activity activity;
        try {
            List<String> list = new ArrayList<>();
            list.add(clubId.toString());
            list.add(nombre);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);

            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/club/activity/byName").header("Content-Type", "application/json").body(json).asJson();
            activity = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return activity;
    }

    public Club getClubByNombre(String nombreClub) {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        Club club;
        try {
            List<String> list = new ArrayList<>();
            list.add(nombreClub);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);

            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/club/byName/" + nombreClub).asJson();
            club = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return club;
    }

    public Costs getCompanyCostsForTheMonth(Long companyId, String fechaMonthYear) {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        Costs cost;
        try {
            List<String> list = new ArrayList<>();
            list.add(companyId.toString());
            list.add(fechaMonthYear);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/company/costs").header("Content-Type", "application/json").body(json).asJson();
            cost = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return cost;
    }

    public List<CheckIn> getCompanyCheckInsForTheMonth(Long companyId, String fechaMonthYear) {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        List<CheckIn> checkIns;
        List<Employee> employees;
        try {
            List<String> list = new ArrayList<>();
            list.add(companyId.toString());
            list.add(fechaMonthYear);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/company/checkIn").header("Content-Type", "application/json").body(json).asJson();
            checkIns = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
            HttpResponse<JsonNode> apiResponse2 = Unirest.put("http://localhost:8080/company/checkIn").header("Content-Type", "application/json").body(json).asJson();
            employees = mapper.readValue(apiResponse2.getBody().toString(), new TypeReference<>() {
            });
            int count = 0;

            for (Employee employee : employees) {
                checkIns.get(count).setEmployee(employee);
                count++;
            }
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return checkIns;
    }

    public Costs getTotalCompanyCostsForTheMonth(Long companyId, String fechaMonthYear) {
        List<CheckIn> checkIns = getCompanyCheckInsForTheMonth(companyId, fechaMonthYear);
        Costs cost = getCompanyCostsForTheMonth(companyId, fechaMonthYear);
        cost.setCheckIns(checkIns);
        return cost;
    }

    public Costs getClubEarningsForTheMonth(Long clubId, String fechaMonthYear) {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        Costs cost;
        try {
            List<String> list = new ArrayList<>();
            list.add(clubId.toString());
            list.add(fechaMonthYear);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/club/earnings").header("Content-Type", "application/json").body(json).asJson();
            cost = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return cost;
    }

    public List<List> getClubCheckInsForTheMonth(Long clubId, String fechaMonthYear) {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        List<List> checkIns;
        try {
            List<String> list = new ArrayList<>();
            list.add(clubId.toString());
            list.add(fechaMonthYear);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
            HttpResponse<JsonNode> apiResponse = Unirest.post("http://localhost:8080/club/checkIn").header("Content-Type", "application/json").body(json).asJson();
            checkIns = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });

        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return checkIns;
    }

    public List<Employee> getClubCheckInsForTheMonthEmployees(Long clubId, String fechaMonthYear) {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        List<Employee> employees;
        try {
            List<String> list = new ArrayList<>();
            list.add(clubId.toString());
            list.add(fechaMonthYear);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);

            HttpResponse<JsonNode> apiResponse2 = Unirest.put("http://localhost:8080/club/checkIn").header("Content-Type", "application/json").body(json).asJson();
            employees = mapper.readValue(apiResponse2.getBody().toString(), new TypeReference<>() {
            });

        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return employees;
    }

    public HttpResponse<JsonNode> deleteClubUser(String cedula) {
        try {
            return Unirest.delete("http://localhost:8080/club/user/" + cedula).asJson();
        } catch (UnirestException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<List> getPendingReservations(Long appUserId) {
        ObjectMapper mapper = new ObjectMapper();
        List<List> reservations;

        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/employee/reservations/" + appUserId).asJson();
            reservations = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
        } catch (UnirestException | IOException ex) {
            throw new RuntimeException(ex);
        }
        return reservations;
    }
    public HttpResponse<JsonNode> cancelReservation(AppUser appUser, String nombreAct, String diaAct, String horaInicio,String fechaAct) {
        String json;
        HttpResponse<JsonNode> apiResponse;
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<String> list = new ArrayList<>();
            list.add(appUser.getId().toString());
            list.add(nombreAct);
            list.add(diaAct);
            list.add(horaInicio);
            list.add(fechaAct);
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
            apiResponse = Unirest.post("http://localhost:8080/club/activity/register/cancel")
                    .header("Content-Type", "application/json")
                    .body(json).asJson();
        } catch (UnirestException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return apiResponse;
    }

    public void insertCosas(){
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        //admin
        appService.addNewAdmin("admin", "admin123");
        //companies
        appService.addNewCompany("Apple", 12345678L, "apple@icloud.com", "apple123");
        appService.addNewCompany("Microsoft", 87654321L, "microsoft@outlook.com", "microsoft123");
        appService.addNewCompany("Google", 81726354L, "google@gmail.com", "google123");
        //clubs
        appService.addNewClub("Biguá", "Vázquez Ledesma 2968", "bigua@gmail.com", "bigua123");
        appService.addNewClub("Malvin", "Av. Legrand 5163", "malvin@gmail.com", "malvin123");
        appService.addNewClub("Trouville", "Alejandro Chucarro 1031", "trouville@gmail.com", "trouville123");
        ///employees
        appService.addNewEmployee(111L, appService.getListOfCompanies().get(0), 1000L, "apple1@icloud.com", "apple123");
        appService.addNewEmployee(112L, appService.getListOfCompanies().get(0), 1500L, "apple2@icloud.com", "apple123");
        appService.addNewEmployee(113L, appService.getListOfCompanies().get(0), 2000L, "apple3@icloud.com", "apple123");
        appService.addNewEmployee(211L, appService.getListOfCompanies().get(1), 1500L, "microsoft1@outlook.com", "microsoft123");
        appService.addNewEmployee(212L, appService.getListOfCompanies().get(1), 2000L, "microsoft2@outlook.com", "microsoft123");
        appService.addNewEmployee(211L, appService.getListOfCompanies().get(1), 2500L, "microsoft2@outlook.com", "microsoft123");
        appService.addNewEmployee(311L, appService.getListOfCompanies().get(2), 2000L, "google1@gmail.com", "google123");
        appService.addNewEmployee(312L, appService.getListOfCompanies().get(2), 2500L, "google2@gmail.com", "google123");
        appService.addNewEmployee(313L, appService.getListOfCompanies().get(2), 3000L, "google3@gmail.com", "google123");
        //activities
        appService.addNewActivity(appService.getListOfClubs().get(0), "Fútbol5", 120L, crearHorarios(10), ActivityCategories.Deportes_En_Equipo);
        appService.addNewActivity(appService.getListOfClubs().get(0), "Basketball", 150L, crearHorarios(10), ActivityCategories.Deportes_En_Equipo);
        appService.addNewActivity(appService.getListOfClubs().get(0), "Gimnasio", 80L, crearHorarios2(), ActivityCategories.Gimnasio);
        appService.addNewActivity(appService.getListOfClubs().get(1), "Handball", 100L, crearHorarios(14), ActivityCategories.Deportes_En_Equipo);
        appService.addNewActivity(appService.getListOfClubs().get(1), "Basketball", 140L, crearHorarios(10), ActivityCategories.Deportes_En_Equipo);
        appService.addNewActivity(appService.getListOfClubs().get(1), "Piscina", 80L, crearHorarios2(), ActivityCategories.Piscina);
        appService.addNewActivity(appService.getListOfClubs().get(2), "Crossfit", 2000L, crearHorarios(15), ActivityCategories.Clases);
        appService.addNewActivity(appService.getListOfClubs().get(2), "Basketball", 145L, crearHorarios(10), ActivityCategories.Deportes_En_Equipo);
        appService.addNewActivity(appService.getListOfClubs().get(2), "Baile", 1L, crearHorarios(-1), ActivityCategories.Clases);
        //images
        appService.uploadActivityPicture(new File("src/main/resources/futbol5.png"), 1L);
        appService.uploadActivityPicture(new File("src/main/resources/basketball.png"), 2L);
        appService.uploadActivityPicture(new File("src/main/resources/gimnasio.png"), 3L);
        appService.uploadActivityPicture(new File("src/main/resources/handball.png"), 4L);
        appService.uploadActivityPicture(new File("src/main/resources/basketball2.png"), 5L);
        appService.uploadActivityPicture(new File("src/main/resources/nado.png"), 6L);
        appService.uploadActivityPicture(new File("src/main/resources/crossfit.png"), 7L);
        appService.uploadActivityPicture(new File("src/main/resources/basketball3.png"), 8L);
        appService.uploadActivityPicture(new File("src/main/resources/baile.png"), 9L);


    }

    public List<Quota> crearHorarios2(){
        List<Quota> quotas = new ArrayList<>();
        quotas.add(new Quota(DayOfWeek.MONDAY.toString(), "06:00:00", "22:00:00", -1));
        quotas.add(new Quota(DayOfWeek.TUESDAY.toString(), "06:00:00", "22:00:00", -1));
        quotas.add(new Quota(DayOfWeek.WEDNESDAY.toString(), "06:00:00", "22:00:00", -1));
        quotas.add(new Quota(DayOfWeek.THURSDAY.toString(), "06:00:00", "22:00:00", -1));
        quotas.add(new Quota(DayOfWeek.FRIDAY.toString(), "06:00:00", "22:00:00", -1));
        quotas.add(new Quota(DayOfWeek.SATURDAY.toString(), "06:00:00", "22:00:00", -1));
        quotas.add(new Quota(DayOfWeek.SUNDAY.toString(), "06:00:00", "22:00:00", -1));
        return quotas;
    }
    public List<Quota> crearHorarios(int maxCupos){
        List<Quota> quotas = new ArrayList<>();
        quotas.add(new Quota(DayOfWeek.MONDAY.toString(), "07:00:00", "08:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.MONDAY.toString(), "08:00:00", "09:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.MONDAY.toString(), "09:00:00", "10:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.MONDAY.toString(), "10:00:00", "11:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.MONDAY.toString(), "11:00:00", "12:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.MONDAY.toString(), "12:00:00", "13:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.MONDAY.toString(), "16:00:00", "17:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.MONDAY.toString(), "17:00:00", "18:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.MONDAY.toString(), "18:00:00", "19:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.MONDAY.toString(), "19:00:00", "20:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.TUESDAY.toString(), "07:00:00", "08:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.TUESDAY.toString(), "08:00:00", "09:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.TUESDAY.toString(), "09:00:00", "10:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.TUESDAY.toString(), "10:00:00", "11:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.TUESDAY.toString(), "11:00:00", "12:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.TUESDAY.toString(), "12:00:00", "13:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.TUESDAY.toString(), "16:00:00", "17:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.TUESDAY.toString(), "17:00:00", "18:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.TUESDAY.toString(), "18:00:00", "19:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.TUESDAY.toString(), "19:00:00", "20:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.WEDNESDAY.toString(), "07:00:00", "08:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.WEDNESDAY.toString(), "08:00:00", "09:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.WEDNESDAY.toString(), "09:00:00", "10:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.WEDNESDAY.toString(), "10:00:00", "11:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.WEDNESDAY.toString(), "11:00:00", "12:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.WEDNESDAY.toString(), "12:00:00", "13:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.WEDNESDAY.toString(), "16:00:00", "17:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.WEDNESDAY.toString(), "17:00:00", "18:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.WEDNESDAY.toString(), "18:00:00", "19:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.WEDNESDAY.toString(), "19:00:00", "20:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.THURSDAY.toString(), "07:00:00", "08:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.THURSDAY.toString(), "08:00:00", "09:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.THURSDAY.toString(), "09:00:00", "10:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.THURSDAY.toString(), "10:00:00", "11:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.THURSDAY.toString(), "11:00:00", "12:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.THURSDAY.toString(), "12:00:00", "13:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.THURSDAY.toString(), "16:00:00", "17:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.THURSDAY.toString(), "17:00:00", "18:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.THURSDAY.toString(), "18:00:00", "19:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.THURSDAY.toString(), "19:00:00", "20:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.FRIDAY.toString(), "07:00:00", "08:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.FRIDAY.toString(), "08:00:00", "09:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.FRIDAY.toString(), "09:00:00", "10:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.FRIDAY.toString(), "10:00:00", "11:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.FRIDAY.toString(), "11:00:00", "12:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.FRIDAY.toString(), "12:00:00", "13:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.FRIDAY.toString(), "16:00:00", "17:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.FRIDAY.toString(), "17:00:00", "18:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.FRIDAY.toString(), "18:00:00", "19:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.FRIDAY.toString(), "19:00:00", "20:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.SATURDAY.toString(), "09:00:00", "10:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.SATURDAY.toString(), "10:00:00", "11:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.SATURDAY.toString(), "11:00:00", "12:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.SATURDAY.toString(), "16:00:00", "17:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.SATURDAY.toString(), "17:00:00", "18:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.SATURDAY.toString(), "18:00:00", "19:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.SATURDAY.toString(), "19:00:00", "20:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.SUNDAY.toString(), "09:00:00", "10:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.SUNDAY.toString(), "10:00:00", "11:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.SUNDAY.toString(), "11:00:00", "12:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.SUNDAY.toString(), "16:00:00", "17:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.SUNDAY.toString(), "17:00:00", "18:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.SUNDAY.toString(), "18:00:00", "19:00:00", maxCupos));
        quotas.add(new Quota(DayOfWeek.SUNDAY.toString(), "19:00:00", "20:00:00", maxCupos));
        return quotas;
    }
}




