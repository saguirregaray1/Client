package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
import com.example.clientTIC.AppUserRole;
import com.example.clientTIC.models.ActivityCategories;
import com.example.clientTIC.models.Quota;
import com.example.clientTIC.spring.AppService;
import com.example.clientTIC.spring.ApplicationContextProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;


public class loginController {

    @FXML
    public AnchorPane Pane_base;

    @FXML
    public TextField emailTextField;

    @FXML
    public PasswordField passwordTextField;

    @FXML
    public javafx.scene.control.Button LogInButton;

    @FXML
    private Label notificationLabel;


    @FXML
    void AdminButtonClick(ActionEvent event) throws IOException {
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        //insertCosas();
        insertCosas();

        HttpResponse<JsonNode> apiResponse = appService.login(email, password);

        if (apiResponse.getStatus() != 200) { //?
            notificationLabel.setText("Usuario o contraseña incorrecta");
        } else {
            ObjectMapper mapper = new ObjectMapper();
            AppUser appUser = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<>() {
            });
            if (appUser.getAppUserRole().equals(AppUserRole.ADMIN)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
                Parent root = loader.load();
                AdminController adminController = loader.getController();
                adminController.displayName(appUser.getEmail());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else if (appUser.getAppUserRole().equals(AppUserRole.EMPLOYEE)) {
                appUser.setEmployee(appService.appUserGetEmployee(appUser.getId()));
                FXMLLoader loader = new FXMLLoader(UserViewController.class.getResource("UserView.fxml"));
                UserViewController userViewController = new UserViewController();
                userViewController.setAppUser(appUser);
                loader.setController(userViewController);
                Parent root = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else if (appUser.getAppUserRole().equals(AppUserRole.CLUB_USER)) {
                appUser.setClub(appService.appUserGetClub(appUser.getId()));
                FXMLLoader loader = new FXMLLoader(ClubListViewController.class.getResource("ClubsListView.fxml"));
                ClubListViewController clubListViewController = new ClubListViewController();
                clubListViewController.setAppUser(appUser);
                loader.setController(clubListViewController);
                Parent root = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else if (appUser.getAppUserRole().equals(AppUserRole.COMPANY_USER)) {
                appUser.setCompany(appService.appUserGetCompany(appUser.getId()));
                FXMLLoader loader = new FXMLLoader(CompanyViewController.class.getResource("CompanyView.fxml"));
                CompanyViewController companyViewController = new CompanyViewController();
                loader.setController(companyViewController);
                Parent root = loader.load();
                companyViewController.setAppuser(appUser);
                companyViewController.setListOfEmployees();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    private void insertCosas(){
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        //admin
        appService.addNewAdmin("admin", "admin123");
        //companies
        appService.addNewCompany("Apple", 12345678L, "apple@icloud.com", "apple123");
        appService.addNewCompany("Microsoft", 87654321L, "microsoft@outlook.com", "microsoft123");
        appService.addNewCompany("Google", 81726354L, "google@gmail.com", "google123");
        //clubs
        appService.addNewClub("Biguá", "Vázquez Ledesma 2968", "biguauser@gmail.com", "bigua123");
        appService.addNewClub("Malvin", "Av. Legrand 5163", "malvinuser@gmail.com", "malvin123");
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

    List<Quota> crearHorarios2(){
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
    List<Quota> crearHorarios(int maxCupos){
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

    @FXML
    void cancelButton() {
        System.exit(0);
    }
}

