package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
import com.example.clientTIC.AppUserRole;
import com.example.clientTIC.models.ActivityCategories;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

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
    void AdminButtonClick(ActionEvent event) throws IOException, UnirestException {
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        appService.addNewCompany("coca", 123L);
        appService.addNewEmployee(123L,1L,1000L,"abc","bb");
        appService.addNewClub("um","18");
        appService.addNewActivity(1L,"futbol",1000L,100, ActivityCategories.CATEGORY_1);

        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        HttpResponse<JsonNode> apiResponse=appService.login(email,password);

        if (apiResponse.getStatus()==400){ //?
            throw new IllegalStateException("usuario o contraseña incorrecta");
            }
        else {
            ObjectMapper mapper = new ObjectMapper();
            AppUser appUser = mapper.readValue(apiResponse.getBody().toString(), new TypeReference<AppUser>(){});
            if(appUser.getAppUserRole().equals(AppUserRole.ADMIN)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
                Parent root = loader.load();

                AdminController adminController = loader.getController();
                adminController.displayName(appUser.getEmail());

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else if (appUser.getAppUserRole().equals(AppUserRole.EMPLOYEE)) {
                FXMLLoader loader = new FXMLLoader(UserViewController.class.getResource("UserView.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    @FXML
    void cancelButton(){System.exit(0);}
}

