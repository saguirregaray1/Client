package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
import com.example.clientTIC.models.Activity;
import com.example.clientTIC.models.ActivityCategories;
import com.example.clientTIC.spring.AppService;
import com.example.clientTIC.spring.ApplicationContextProvider;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
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
      //  setListOfActivities(appService.getListOfActivitiesByCategory(category));
        }

    @FXML
    protected void showFavorites(AppUser appUser, com.example.clientTIC.models.Activity activity){
       //boton
        activityBox.getChildren().clear();
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
      //  setListOfActivities(appService.getListOfFavs(appUser));
        }

    @FXML
    protected void registerToActivity(AppUser appUser, Activity activity){
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        HttpResponse<JsonNode> request= appService.registerToActivity(appUser,activity);
        if (request.getStatus()!=200){
            throw new IllegalStateException("no se registró");
        }
        //update atributos
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        filter.getItems().addAll(categorias);
        filter.setOnAction(this::setFilter);

        setListOfActivities(appService.getListOfActivities());
    }

    private void setListOfActivities(List<List> activityList){
        for(int i = 0; i<activityList.size();i++){
            HBox hBox = new HBox(25);
            Button button = new Button("Registrarse");
            List list= activityList.get(i);
            Activity activity = new Activity((String) list.get(1), Long.valueOf(list.get(2).toString()), (Integer) list.get(3),ActivityCategories.valueOf((String) list.get(4)));
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    registerToActivity(appUser,activity);
                }
            });
            //Image image = new Image("images.jpg");
            //ImageView imageView = new ImageView(image);
            //imageView.setFitHeight(100);
            //imageView.setFitWidth(150);
            Label labelName = new Label(activity.getNombre());
            labelName.setMaxWidth(250);
            labelName.setMaxHeight(100);
            labelName.setWrapText(true);
            labelName.setStyle("-fx-font-weight: bold");
            labelName.setFont(new Font("Arial",20));
            Label labelPrice = new Label(" Precio: "+ activity.getPrecio());
            labelPrice.setMaxWidth(250);
            labelPrice.setMaxHeight(100);
            labelPrice.setWrapText(true);
            labelPrice.setFont(new Font("Arial",20));
            Label labelCupos = new Label(" Cupos disponibles: "+ activity.getCupos());
            labelCupos.setMaxWidth(250);
            labelCupos.setMaxHeight(100);
            labelCupos.setWrapText(true);
            labelCupos.setFont(new Font("Arial",20));
            hBox.setAlignment(Pos.CENTER);
            hBox.setPadding(new Insets(5,5,5,5));
            Separator separator1 = new Separator(Orientation.VERTICAL);
            Separator separator2 = new Separator(Orientation.VERTICAL);
            Separator separator3 = new Separator(Orientation.VERTICAL);
            Separator separator4 = new Separator(Orientation.VERTICAL);
            hBox.setStyle("-fx-border-color: transparent transparent #263f78 transparent;");
            hBox.getChildren().addAll(separator1,labelName,separator2,labelPrice,
                    separator3,labelCupos,separator4,button);
            activityBox.getChildren().add(hBox);
        }
    }

}
