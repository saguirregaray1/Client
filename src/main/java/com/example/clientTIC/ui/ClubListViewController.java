package com.example.clientTIC.ui;

import com.example.clientTIC.spring.AppService;
import com.example.clientTIC.spring.ApplicationContextProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.Text;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ClubListViewController implements Initializable {

    @FXML
    private Button returnButton;

    @FXML
    private TextField userCheckIn;

    @FXML
    private Label checkInResult;

    @FXML
    private VBox imagesActivity;

    @FXML
    private VBox horariosActivity;

    @FXML
    private TextField createActivityName;

    @FXML
    private CheckBox hastimeActivity;

    @FXML
    private AnchorPane timeButtonAnchorpane;

    @FXML
    private TextField deleteUserID;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("volver.png");
        ImageView img = new ImageView(image);
        returnButton.setGraphic(img);
    }

    @FXML
    protected void createActivityButton(){

    }

    @FXML
    protected void chargeImagesButton(ActionEvent event){
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(((Node) event.getTarget()).getScene().getWindow());
        if (file != null) {
            Image image1 = new Image(file.toURI().toString());
            ImageView ip = new ImageView(image1);
            imagesActivity.getChildren().add(ip);
            }
    }

    @FXML
    protected void registerUserButton(){

    }

    @FXML
    protected void deleteUserButton(){
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        String cedula = deleteUserID.getText();
        appService.deleteEmployees(cedula);

    }

    @FXML
    protected void verifyButton(){
        String name= userCheckIn.getText();
    }







}
