package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
import com.example.clientTIC.models.Activity;
import com.example.clientTIC.models.ActivityCategories;
import com.example.clientTIC.spring.AppService;
import com.example.clientTIC.spring.ApplicationContextProvider;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserViewController extends ListView<Activity> implements Initializable {

    public AppUser appUser;

    //ejemplo de categorias para el filtrado
    ObservableList<String> categorias = FXCollections.observableArrayList(
            "Categoria 1",
            "Categoria 2",
            "Categoria 3",
            "Categoria 4");

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
        }

    @FXML
    protected void showFavorites(AppUser appUser, Activity activity){
        activityBox.getChildren().clear();
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        }

    @FXML
    protected void registerToActivity(AppUser appUser, Activity activity){
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        if (appService.registerToActivity(appUser,activity).getStatus()!=200){
            throw new IllegalStateException("no se registró");
        }
        appService.registerToActivity(appUser,activity);
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        filter.getItems().addAll(categorias);
        filter.setOnAction(this::setFilter);
    }

    private void setListOfActivities(ObservableList<List> activityList){
        for(int i = 0; i<activityList.size();i++){
            HBox hBox = new HBox(40);
            Button button = new Button("Registrarse");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //registerToActivity(appUser,activityList.get(i));
                }
            });
            //Image image = new Image("images.jpg");
            //ImageView imageView = new ImageView(image);
            //imageView.setFitHeight(100);
            //imageView.setFitWidth(150);
            Label label = new Label("Titulo o descripcion de la actividad");
            label.setMaxWidth(300);
            label.setMaxHeight(100);
            label.setWrapText(true);
            hBox.setAlignment(Pos.CENTER);
            hBox.setPadding(new Insets(5,5,5,5));
            Separator separator1 = new Separator(Orientation.VERTICAL);
            Separator separator2 = new Separator(Orientation.VERTICAL);
            hBox.setStyle("-fx-border-color: transparent transparent #263f78 transparent;");
            hBox.getChildren().addAll(separator1,label,separator2,button);
            activityBox.getChildren().add(hBox);
        }
    }

}
