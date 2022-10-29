package com.example.clientTIC.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.Text;

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
    protected void chargeImagesButton(){

    }

    @FXML
    protected void registerUserButton(){

    }

    @FXML
    protected void deleteUserButton(){

    }

    @FXML
    protected void verifyButton(){

    }







}
