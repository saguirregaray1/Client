package com.example.clientTIC.UI;

import com.example.clientTIC.Spring.AppService;
import com.example.clientTIC.Spring.ApplicationContextProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.vertx.core.json.Json;
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

public class loginControler {

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
        appService.addNewAdmin("a","b");

        String mail = emailTextField.getText();
        String password = passwordTextField.getText();

        HttpResponse<JsonNode> apiResponse=appService.login(mail,password);

        if (apiResponse.getStatus()==400){ //?
            throw new IllegalStateException("usuario o contraseña incorrecta");
            }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
            Parent root = loader.load();

            AdminController adminController = loader.getController();
            adminController.displayName(mail);


            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene= new Scene(root);
            stage.setScene(scene);
            stage.show();
            }
    }

    @FXML
    void cancelButton(){System.exit(0);}
}

    /*btn.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent e) {
            ApplicationContext applicationContext= ApplicationContextProvider.getApplicationContext();
            Service service =applicationContext.getBean(Service.class);
            service.addNewClub(userTextField.getText(),pwBox.getText(),dirText.getText());
            service.getListOfClubs();
            //   service.deleteClubs("eee");


            //  service.addNewCompany(userTextField.getText(),pwBox.getText(),dirText.getText());
            //  service.getListOfCompanies();
            //  service.deleteCompanies("aaa");
            //  service.addNewEmployee(userTextField.getText(),pwBox.getText(),dirText.getText());
            //  service.getListOfEmployees();
            //  service.deleteEmployees("123");
        }
    });*/
