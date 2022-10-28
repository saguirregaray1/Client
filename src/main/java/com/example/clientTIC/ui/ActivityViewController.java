package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
import com.example.clientTIC.models.Activity;
import com.example.clientTIC.spring.AppService;
import com.example.clientTIC.spring.ApplicationContextProvider;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ActivityViewController {

    private AppUser currentAppUser;

    public void setCurrentAppUser(AppUser currentAppUser) {
        this.currentAppUser = currentAppUser;
    }

    private Activity currentActivity;

    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }

    @FXML
    private Button returnButton;

    @FXML
    private Button registerButton;

    @FXML
    public VBox imagesActivity;

    @FXML
    public VBox horariosActivity;

    @FXML
    public Label nameActivity;

    @FXML
    public Label clubNameActivity;

    @FXML
    public Label activityCost;

    public void setInfo(String nombreActividad, /*String nombreClub,*/ String costoActividad, List<Image> activityImages){
        nameActivity.setText(nombreActividad);
        //clubNameActivity.setText("Venga a "+nombreClub+" y disfrute de una buena jornada deportiva");
        if (activityCost!=null){
            activityCost.setText("Costo de la actividad: " + costoActividad);
        }
        for (int i =0; i<activityImages.size(); i++){
            Image imagen = activityImages.get(i);
            ImageView imageView = new ImageView(imagen);
            imageView.setFitHeight(100);
            imageView.setFitWidth(150);
            imagesActivity.getChildren().add(imageView);
        }

    }

    @FXML
    protected void setReturnButton(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void setRegisterButton(ActionEvent event){
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        HttpResponse<JsonNode> request= appService.registerToActivity(currentAppUser,currentActivity.getId());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




}
