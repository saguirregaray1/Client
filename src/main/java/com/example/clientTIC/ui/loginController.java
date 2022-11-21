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
            //    adminController.displayName(appUser.getEmail());
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


    @FXML
    void cancelButton() {
        System.exit(0);
    }
}

