package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
import com.example.clientTIC.models.Activity;
import com.example.clientTIC.models.ActivityCategories;
import com.example.clientTIC.spring.AppService;
import com.example.clientTIC.spring.ApplicationContextProvider;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserViewController extends ListView<Activity> implements Initializable {

    public AppUser appUser;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    //ejemplo de categorias para el filtrado
    ObservableList<String> categorias = FXCollections.observableArrayList(ActivityCategories.CATEGORY_1.toString(),
            ActivityCategories.CATEGORY_2.toString(),
            ActivityCategories.CATEGORY_3.toString(),
            ActivityCategories.CATEGORY_4.toString());

    @FXML
    private VBox activityBox;

    @FXML
    private ChoiceBox<String> filter;

    @FXML
    void searchClubs(ActionEvent event2) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClubsListView.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)event2.getSource()).getScene().getWindow();
        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void setFilter(ActionEvent event){
        activityBox.getChildren().clear();
        String category = filter.getValue();
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        setListOfActivities(appService.getListOfActivitiesByCategory(category));
    }

    @FXML
    protected void mostrarFavoritos(ActionEvent event){
       //boton
        activityBox.getChildren().clear();
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        setListOfActivities(appService.getListOfFavs(this.appUser));
    }

    @FXML
    protected void registerToActivity(AppUser appUser, Long activityId){
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        HttpResponse<JsonNode> request= appService.registerToActivity(appUser,activityId);
        if (request.getStatus()!=200){
            throw new IllegalStateException("no se registró");
        }
        activityBox.getChildren().clear();
        setListOfActivities(appService.getListOfActivities());
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        filter.getItems().addAll(categorias);

        filter.setOnAction(this::setFilter);
        setListOfActivities(appService.getListOfActivities());
    }

    private void setListOfActivities(List<List> activityList){
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        for (List value : activityList) {
            HBox hBox = new HBox(25);
            Button button = new Button("Registrarse");
            Activity activity = new Activity((String) value.get(0), Long.valueOf(value.get(1).toString()), (Integer) value.get(2), ActivityCategories.valueOf((String) value.get(3)));
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    registerToActivity(appUser, Long.valueOf(value.get(6).toString()));
                }
            });
            List<Image> pictures = appService.getActivityImages(Long.valueOf(value.get(6).toString()));
            ImageView imageView = new ImageView(pictures.get(0));
            imageView.setFitHeight(100);
            imageView.setFitWidth(150);
            Button activityButton= new Button();
            activityButton.setGraphic(imageView);
            activityButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ActivityView.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ActivityViewController activityViewController = loader.getController();
                    activityViewController.setCurrentActivity(activity);
                    activityViewController.setCurrentAppUser(appUser);
                    activityViewController.setInfo(activity.getNombre(),/*activity.getClub().getNombre(),*/
                            String.valueOf(activity.getPrecio()),appService.getActivityImages(activity.getId()));
                    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    Scene scene= new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            });
            Label labelName = new Label(activity.getNombre());
            labelName.setMaxWidth(250);
            labelName.setMaxHeight(100);
            labelName.setWrapText(true);
            labelName.setStyle("-fx-font-weight: bold");
            labelName.setFont(new Font("Arial", 20));
            Label labelPrice = new Label(" Precio: " + activity.getPrecio());
            labelPrice.setMaxWidth(250);
            labelPrice.setMaxHeight(100);
            labelPrice.setWrapText(true);
            labelPrice.setFont(new Font("Arial", 20));
            Label labelCupos = new Label(" Cupos disponibles: " + activity.getCupos());
            labelCupos.setMaxWidth(250);
            labelCupos.setMaxHeight(100);
            labelCupos.setWrapText(true);
            labelCupos.setFont(new Font("Arial", 20));
            hBox.setAlignment(Pos.CENTER);
            hBox.setPadding(new Insets(5, 5, 5, 5));
            HBox.setHgrow(activityButton, Priority.ALWAYS);
            HBox.setHgrow(activityButton, Priority.ALWAYS);
            HBox.setHgrow(activityButton, Priority.ALWAYS);
            HBox.setHgrow(activityButton, Priority.ALWAYS);
            hBox.setStyle("-fx-border-color: transparent transparent #263f78 transparent;");
            hBox.getChildren().addAll(activityButton, labelName,labelPrice,labelCupos,button);
            activityBox.getChildren().add(hBox);
        }
    }

}
