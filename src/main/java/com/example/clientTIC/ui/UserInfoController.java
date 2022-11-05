package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
import com.example.clientTIC.AppUserRole;
import com.example.clientTIC.models.Employee;
import com.example.clientTIC.spring.AppService;
import com.example.clientTIC.spring.ApplicationContextProvider;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserInfoController implements Initializable {

    public AppUser appUser;

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @FXML
    private Button returnButton;


    @FXML
    private Label nameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label saldoLabel;


    @FXML
    protected void volver (ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserView.fxml"));
        Parent root = loader.load();
        UserViewController userViewController = loader.getController();
        userViewController.setAppUser(this.appUser);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        Image image = new Image("volver.png");
        ImageView img = new ImageView(image);
        returnButton.setGraphic(img);
        ObservableList<Employee> employees= appService.getListOfEmployees();
        for (Employee empleado : employees){
            if (this.appUser.getId() == empleado.getId()){
                nameLabel.setText("Cedula: " + String.valueOf(empleado.getCedula()));
                emailLabel.setText("Email: " + empleado.getEmail());
                saldoLabel.setText("Saldo disponible: " + String.valueOf(empleado.getSaldo()));
            }
        }
    }
}
