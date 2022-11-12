package com.example.clientTIC.ui;

import com.example.clientTIC.models.*;
import com.example.clientTIC.spring.AppService;
import com.example.clientTIC.spring.ApplicationContextProvider;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CheckInClubController implements Initializable {

    private Club currentClub;

    public void setCurrentClub(Club currentClub) {
        this.currentClub = currentClub;
    }

    @FXML
    private VBox costsBox;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee,String> idColumn;

    @FXML
    private Label gananciasLabel;

    @FXML
    protected void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClubAdminView.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("cedula"));
        setCosts();
    }

    void setCosts(){
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        Costs costs = appService.getClubEarningsForTheMonth(currentClub.getId(),"11/2022");
        List<CheckIn> checks = costs.getCheckIns();
        ObservableList<Employee> empleados = (ObservableList<Employee>) costs.getUsers();
        gananciasLabel.setText("Ganancias totales: " +costs.getTotal());
        for(CheckIn check : checks){
            HBox checkInBox = new HBox(10);
            Quota currenQuota = check.getQuota();
            String fecha = check.getFecha();
            Label fechaLabel = new Label("Fecha: " + fecha);
            Label diaLabel = new Label("Dia: " +currenQuota.getDay());
            Label horaInicio= new Label("Hora inicio:" + currenQuota.getStartTime());
            Button mostrar = new Button("Ver empleados");
            mostrar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    employeeTable.setItems(empleados);
                }
            });
            fechaLabel.setStyle("-fx-font-weight: bold");
            fechaLabel.setFont(new Font("Arial", 14));
            diaLabel.setStyle("-fx-font-weight: bold");
            diaLabel.setFont(new Font("Arial", 14));
            horaInicio.setStyle("-fx-font-weight: bold");
            horaInicio.setFont(new Font("Arial", 14));
            HBox.setHgrow(fechaLabel, Priority.ALWAYS);
            HBox.setHgrow(diaLabel, Priority.ALWAYS);
            checkInBox.getChildren().addAll(fechaLabel,diaLabel,horaInicio,mostrar);
            costsBox.getChildren().add(checkInBox);
        }
    }
}