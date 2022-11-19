package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
import com.example.clientTIC.models.Activity;
import com.example.clientTIC.models.Quota;
import com.example.clientTIC.spring.AppService;
import com.example.clientTIC.spring.ApplicationContextProvider;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ActivityViewController implements Initializable {

    private AppUser currentAppUser;

    public void setCurrentAppUser(AppUser currentAppUser) {
        this.currentAppUser = currentAppUser;
    }

    private Activity currentActivity;

    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }

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

    @FXML
    private Label notificationLabel;

    private void setImagesActivity(){
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        List<Image> images = appService.getActivityImages(currentActivity.getId());
        for(Image imagen : images){
            ImageView img = new ImageView(imagen);
            img.setFitHeight(100);
            img.setFitWidth(150);
            imagesActivity.getChildren().add(img);
        }
    }


    private void setInfo(String nombreActividad, String costoActividad){
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        nameActivity.setText("Actividad: "+ nombreActividad);
        /*if(nombreClub!=null) {
            clubNameActivity.setText("Venga a " + nombreClub + " y disfrute de una buena jornada deportiva");
        }*/
        if (activityCost!=null){
            activityCost.setText("Costo de la actividad: " + costoActividad);
        }
        List<Quota> horarios= appService.getActivityQuota(Long.parseLong(currentActivity.getId().toString()));
        for (Quota horario : horarios){
            HBox hBox= new HBox(20);
            String cupos = String.valueOf(horario.getMaxCupos());
            Label diaLabel = new Label("Hora inicio: "+ horario.getStartTime());
            Label cuposLabel = new Label("Cupos disponibles:" + cupos);
            HBox.setHgrow(cuposLabel, Priority.ALWAYS);
            Button registrarAHorario = new Button("Reservar");
            registrarAHorario.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    HttpResponse<JsonNode> response= appService.makeReservation(currentAppUser,LocalDate.now().toString(),horario.getQuotaId().toString());
                    if (response.getStatus()==200){
                        notificationLabel.setText("Reservado correctamente");
                    }
                    else{
                        notificationLabel.setText(response.getBody().toString());
                    }
                }
            });
            hBox.getChildren().addAll(diaLabel,cuposLabel,registrarAHorario);
            horariosActivity.getChildren().add(hBox);
        }
    }

    @FXML
    protected void setReturnButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserView.fxml"));
        UserViewController userViewController = new UserViewController();
        userViewController.setAppUser(this.currentAppUser);
        loader.setController(userViewController);
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void setRegisterButton(ActionEvent event){
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        //fixme
     //  HttpResponse<JsonNode> request=*/ appService.registerToActivity(currentAppUser,currentActivity.getId(),1L);
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setInfo(currentActivity.getNombre(), String.valueOf(currentActivity.getPrecio()));
        setImagesActivity();
    }
}
