package com.example.clientTIC.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class AdminController {

    @FXML
    public TextField emailTextField;

    @FXML
    public PasswordField passwordTextField;

    @FXML
    public Button loginButton;

    @FXML
    public javafx.scene.control.Label TitleLabel;


    public void displayName(String username){
        TitleLabel.setText("Que desea hacer "+ username+"?");
    }


    @FXML
    void openEntrView(ActionEvent event2) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EnterpriseView.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)event2.getSource()).getScene().getWindow();
        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
