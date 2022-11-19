package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
import com.example.clientTIC.AppUserRole;
import com.example.clientTIC.models.Employee;
import com.example.clientTIC.models.Reservation;
import com.example.clientTIC.spring.AppService;
import com.example.clientTIC.spring.ApplicationContextProvider;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserInfoController implements Initializable {

    public AppUser appUser;

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @FXML
    private Button returnButton;

    @FXML
    private VBox reservationsBox;


    @FXML
    private Label nameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label saldoLabel;

    @FXML
    private Label notificationLabel;


    @FXML
    protected void volver (ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserView.fxml"));
        UserViewController userViewController = new UserViewController();
        userViewController.setAppUser(this.appUser);
        loader.setController(userViewController);
        Parent root = loader.load();
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
        img.setFitHeight(50);
        img.setFitWidth(100);
        returnButton.setBackground(null);
        returnButton.setGraphic(img);
        Employee empleado = appUser.getEmployee();
        nameLabel.setText("Cedula: " + String.valueOf(empleado.getCedula()));
        emailLabel.setText("Email: " + appUser.getEmail());
        saldoLabel.setText("Saldo disponible: " + String.valueOf(empleado.getSaldo()));
        setReservations();
        }

    protected void setReservations(){
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        List<List> reservaciones = appService.getPendingReservations(appUser.getId());
        for (List element : reservaciones){
            HBox box = new HBox(10);
            Label nombreAct = new Label(element.get(2).toString());
            Label diaAct = new Label(element.get(0).toString());
            Label horaInicio = new Label(element.get(1).toString());
            Label fechaAct = new Label(element.get(3).toString());
            Button cancelarReserva = new Button("Cancelar reserva");
            cancelarReserva.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    HttpResponse<JsonNode> apiResponse=appService.cancelReservation(appUser,nombreAct.getText(),diaAct.getText(),horaInicio.getText(),fechaAct.getText());
                    if (apiResponse.getStatus()==200){
                        notificationLabel.setText("Reserva cancelada correctamente");
                        reservationsBox.getChildren().remove(box);
                    }
                    else{
                        notificationLabel.setText(apiResponse.getBody().toString());}
                }
            });
            nombreAct.setStyle("-fx-font-weight: bold");
            diaAct.setStyle("-fx-font-weight: bold");
            horaInicio.setStyle("-fx-font-weight: bold");
            HBox.setHgrow(horaInicio, Priority.ALWAYS);
            HBox.setHgrow(diaAct, Priority.ALWAYS);
            HBox.setHgrow(nombreAct, Priority.ALWAYS);
            box.getChildren().addAll(nombreAct,diaAct,horaInicio,cancelarReserva);
            reservationsBox.getChildren().add(box);
        }

    }
}



