package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
import com.example.clientTIC.AppUserRole;
import com.example.clientTIC.models.ActivityCategories;
import com.example.clientTIC.models.CheckIn;
import com.example.clientTIC.models.Costs;
import com.example.clientTIC.models.Quota;
import com.example.clientTIC.spring.AppService;
import com.example.clientTIC.spring.ApplicationContextProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
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
    void AdminButtonClick(ActionEvent event) throws IOException, UnirestException {
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
     //   insertCosas();

        HttpResponse<JsonNode> apiResponse = appService.login(email, password);

        if (apiResponse.getStatus() != 200) { //?
            notificationLabel.setText("Usuario o contraseña incorrecta");
        } else {
            ObjectMapper mapper = new ObjectMapper();
            AppUser appUser = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<AppUser>() {
            });
            if (appUser.getAppUserRole().equals(AppUserRole.ADMIN)) {
                //appUser.setAdmin(appService.appUserGetAdmin(appUser.getId()));
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
        //fixme para que funcione el checkIn se debe ajustar el id a la quota correspondiente (1 si es lunes, 2 si es martes etc)
        appService.addNewAdmin("aa", "bb");
        appService.addNewCompany("coca", 123L, "abcd", "222");
        appService.addNewClub("um", "18", "abc", "111");
        appService.addNewEmployee(123L, appService.getListOfCompanies().get(0), 1000L, "aaa", "bbb");
        appService.addNewEmployee(1234L, appService.getListOfCompanies().get(0), 1000L, "a123", "b123");
        List<Quota> quotas = new ArrayList<>();
        quotas.add(new Quota(DayOfWeek.MONDAY.toString(), "00:01:00", "23:59:00", 100));
        quotas.add(new Quota(DayOfWeek.TUESDAY.toString(), "00:01:00", "11:59:00", 100));
        quotas.add(new Quota(DayOfWeek.TUESDAY.toString(), "12:00:00", "23:59:00", 100));
        quotas.add(new Quota(DayOfWeek.WEDNESDAY.toString() +
                "", "00:01:00", "23:59:00", 100));
        quotas.add(new Quota(DayOfWeek.THURSDAY.toString(), "00:01:00", "23:59:00", 100));
        quotas.add(new Quota(DayOfWeek.FRIDAY.toString(), "00:01:00", "23:59:00", 100));
        quotas.add(new Quota(DayOfWeek.SATURDAY.toString() +
                "", "00:01:00", "23:59:00", 100));
        quotas.add(new Quota(DayOfWeek.SUNDAY.toString(), "00:01:00", "23:59:00", 100));
        appService.addNewActivity(appService.getListOfClubs().get(0), "futbol", 120L, quotas, ActivityCategories.CATEGORY_1);
        appService.addNewActivity(appService.getListOfClubs().get(0), "basketball", 1L, quotas, ActivityCategories.CATEGORY_2);
        appService.addFavourite(appService.getListOfEmployees().get(0).getAppUser(), 1L);
        AppUser temp = new AppUser();
        temp.setId(5L);
        appService.makeReservation(temp, "2022-11-15", String.valueOf(2L));
        appService.checkInWithReservation(1234L, "00:01:00", 1L);
        appService.checkInWithReservation(1234L,"00:01:00",2L);
        appService.uploadActivityPicture(new File("src/main/resources/descarga.jpg"), 1L);
        appService.uploadActivityPicture(new File("src/main/resources/python.png"), 2L);

        //  appService.makeReservation(appUser, "2022-11-14", String.valueOf(1L));
        //   appService.checkInWithReservation(123L, "00:01:00", 1L);
       // Costs cost = appService.getTotalCompanyCostsForTheMonth(1L, "2022-11");
       // Costs earnings = appService.getTotalClubEarningsForTheMonth(1L,"2022-11");
        //List<Quota> activityQuota = appService.getActivityQuota(1L);
    }

    @FXML
    void cancelButton() {
        System.exit(0);
    }
}

