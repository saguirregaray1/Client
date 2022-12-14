package com.example.clientTIC.ui;

import com.example.clientTIC.models.Club;
import com.example.clientTIC.spring.AppService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.clientTIC.spring.ApplicationContextProvider.getApplicationContext;

public class ClubsAdminController implements Initializable {


    @FXML
    private TextField createClubName;

    @FXML
    private TextField createClubDir;

    @FXML
    private TextField createClubEmail;

    @FXML
    private TextField createClubPassword;


    @FXML
    private TextField deleteClubName;

    @FXML
    private VBox clubList;

    @FXML
    protected void crearClub(){
        String nombre = createClubName.getText();
        String direccion = createClubDir.getText();
        String email = createClubEmail.getText();
        String password = createClubPassword.getText();
        AppService appService=getApplicationContext().getBean(AppService.class);
        // label
        appService.addNewClub(nombre,direccion,email,password);
        showClubs();
    }

    @FXML
    protected void borrarClub(){
        String nombre = deleteClubName.getText();
        AppService appService=getApplicationContext().getBean(AppService.class);
        appService.deleteClubs(nombre);
        showClubs();
    }

    private void showClubs(){
        clubList.getChildren().clear();
        AppService appService=getApplicationContext().getBean(AppService.class);
        ObservableList<Club> clubs =appService.getListOfClubs();
        for(Club currentClub : clubs){
            HBox hBox = new HBox(10);
            Label nombreLabel = new Label("Nombre: " +currentClub.getNombre());
            Label dirLabel= new Label("Direccion: "+ currentClub.getDir());
            hBox.getChildren().addAll(nombreLabel,dirLabel);
            clubList.getChildren().add(hBox);
        }
    }

    @FXML
    protected void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showClubs();
    }
}
