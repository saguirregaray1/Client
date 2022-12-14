package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
import com.example.clientTIC.models.Activity;
import com.example.clientTIC.models.ActivityCategories;
import com.example.clientTIC.models.Club;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    ObservableList<String> categorias = FXCollections.observableArrayList(ActivityCategories.Deportes_En_Equipo.toString(),
            ActivityCategories.Clases.toString(),
            ActivityCategories.Gimnasio.toString(),
            ActivityCategories.Piscina.toString(),
            "Sin filtro");

    @FXML
    private VBox activityBox;

    @FXML
    private ChoiceBox<String> filter;

    @FXML
    private Button returnButton;

    @FXML
    private ChoiceBox<String> clubsBox;

    @FXML
    private Label notificationLabel;

    @FXML
    protected void volver(ActionEvent event) throws IOException{
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void searchClubs(ActionEvent event2) throws IOException {
        FXMLLoader loader = new FXMLLoader(UserInfoController.class.getResource("UserInfo.fxml"));
        UserInfoController userInfoController = new UserInfoController();
        userInfoController.setAppUser(appUser);
        loader.setController(userInfoController);
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)event2.getSource()).getScene().getWindow();
        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    protected void mostrarFavoritos(ActionEvent event){
       //boton
        activityBox.getChildren().clear();
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        setListOfActivities(appService.getListOfFavs(this.appUser));
    }

    @FXML
    protected void registerToActivity(AppUser appUser,Long scheduleId,String fecha){
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        HttpResponse<JsonNode> request= appService.makeReservation(appUser,scheduleId.toString(),fecha);
        if (request.getStatus()!=200){
            throw new IllegalStateException("no se registr??");
        }
        activityBox.getChildren().clear();
        setListOfActivities(appService.getListOfActivities());
        Image image = new Image("volver.png");
        ImageView img = new ImageView(image);
        returnButton.setGraphic(img);
    }

    @FXML
    private void setActiVityByClub(ActionEvent event){
        activityBox.getChildren().clear();
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        String nombre = clubsBox.getValue();
        Club currentclub = appService.getClubByNombre(nombre);
        setListOfActivities(appService.getListOfActivitiesByClub(currentclub.getId()));
    }

    @FXML
    protected void setFilter(ActionEvent event){
        activityBox.getChildren().clear();
        String category = filter.getValue();
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        if (filter.getValue().equals("Sin filtro")){
            setListOfActivities(appService.getListOfActivities());
        }else{
            setListOfActivities(appService.getListOfActivitiesByCategory(category));
        }

    }

    private void setClubsBox(){
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        ObservableList<Club> clubs =appService.getListOfClubs();
        ArrayList<String> nameList= new ArrayList<>();
        for(Club currentclub: clubs){
            String nombre = currentclub.getNombre();
            nameList.add(nombre);
        }
        ObservableList<String> nameClubs = FXCollections.observableArrayList(nameList);
        clubsBox.getItems().addAll(nameClubs);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        filter.getItems().addAll(categorias);
        setClubsBox();
        filter.setOnAction(this::setFilter);
        clubsBox.setOnAction(this::setActiVityByClub);
        setListOfActivities(appService.getListOfActivities());
        Image image = new Image("volver.png");
        ImageView img = new ImageView(image);
        img.setFitHeight(50);
        img.setFitWidth(100);
        returnButton.setBackground(null);
        returnButton.setGraphic(img);
    }

    private void setListOfActivities(List<List> activityList){
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        for (List value : activityList) {
            HBox hBox = new HBox(15);
            Activity activity = new Activity(Long.valueOf(value.get(5).toString()),(String) value.get(0), Long.valueOf(value.get(1).toString()),ActivityCategories.valueOf((String) value.get(2)));
            String nombreClub=value.get(3).toString();
            String dirClub=value.get(4).toString();
            List<Image> pictures = appService.getActivityImages(Long.valueOf(value.get(5).toString()));
            if (pictures.isEmpty()){
                File file = new File("src/main/resources/logo.png");
                pictures.add(new Image(file.toURI().toString()));
            }
            ImageView imageView = new ImageView(pictures.get(0));
            imageView.setFitHeight(75);
            imageView.setFitWidth(100);
            Button activityButton= new Button() ;
            activityButton.setBackground(null);
            activityButton.setGraphic(imageView);
            activityButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ActivityView.fxml"));
                    ActivityViewController activityViewController = new ActivityViewController();
                    activityViewController.setCurrentActivity(activity);
                    activityViewController.setCurrentAppUser(appUser);
                    loader.setController(activityViewController);
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
            });
            Label labelName = new Label(activity.getNombre());
            Label labelClubName = new Label("Club: " +nombreClub);
            Label labelDirName = new Label("Direccion: " +dirClub);
            labelName.setMaxWidth(150);
            labelName.setMaxHeight(100);
            labelName.setWrapText(true);
            labelName.setStyle("-fx-font-weight: bold");
            labelName.setFont(new Font("Arial", 20));
            labelClubName.setMaxWidth(150);
            labelClubName.setMaxHeight(100);
            labelClubName.setWrapText(true);
            labelClubName.setStyle("-fx-font-weight: bold");
            labelClubName.setFont(new Font("Arial", 20));
            labelDirName.setMaxWidth(150);
            labelDirName.setMaxHeight(100);
            labelDirName.setWrapText(true);
            labelDirName.setStyle("-fx-font-weight: bold");
            labelDirName.setFont(new Font("Arial", 20));
            Label labelPrice = new Label(" Precio: " + activity.getPrecio());
            labelPrice.setMaxWidth(150);
            labelPrice.setMaxHeight(100);
            labelPrice.setWrapText(true);
            labelPrice.setFont(new Font("Arial", 20));
            hBox.setAlignment(Pos.CENTER);
            hBox.setPadding(new Insets(5, 5, 5, 5));
            //HBox.setHgrow(activityButton, Priority.ALWAYS);
            HBox.setHgrow(labelName, Priority.ALWAYS);
            HBox.setHgrow(labelPrice, Priority.ALWAYS);
            Button favoriteButton = new Button("Agregar favoritos");
            favoriteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    appService.addFavourite(appUser,activity.getId());
                    notificationLabel.setText(activity.getNombre()+ " agregada a favoritos");
                }
            });
            hBox.setStyle("-fx-border-color: transparent transparent #263f78 transparent;");
            hBox.getChildren().addAll(activityButton, labelName,labelPrice,labelClubName,labelDirName, favoriteButton);
            activityBox.getChildren().add(hBox);
        }
    }

}
