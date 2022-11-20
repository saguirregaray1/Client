package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
import com.example.clientTIC.models.*;
import com.example.clientTIC.spring.AppService;
import com.example.clientTIC.spring.ApplicationContextProvider;
import javafx.collections.FXCollections;
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
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CheckInClubController implements Initializable {

    private AppUser appUser;

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    private Club currentClub;

    public void setCurrentClub(Club currentClub) {
        this.currentClub = currentClub;
    }

    @FXML
    private VBox costsBox;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee,Long> idColumn;

    @FXML
    private Label gananciasLabel;

    @FXML
    protected void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClubsListView.fxml"));
        ClubListViewController clubListViewController = new ClubListViewController();
        clubListViewController.setAppUser(appUser);
        loader.setController(clubListViewController);
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        setCosts();
        String fecha = LocalDate.now().toString();
        Scanner scanner = new Scanner(fecha);
        scanner.useDelimiter("-");
        String fechaMesAño = scanner.next()+"-"+ scanner.next();
        Costs costs = appService.getClubEarningsForTheMonth(currentClub.getId(),fechaMesAño);
        gananciasLabel.setText("Ganancias del club: "+String.valueOf(costs.getTotal()));
    }

    void setCosts(){
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        String now = LocalDate.now().toString();
        Scanner scanner = new Scanner(now);
        scanner.useDelimiter("-");
        String fechaMesAño = scanner.next()+"-"+ scanner.next();
        Costs costs = appService.getClubEarningsForTheMonth(currentClub.getId(),fechaMesAño);
        List<List> checks = appService.getClubCheckInsForTheMonth(currentClub.getId(),fechaMesAño);
        ObservableList<Employee> empleados = FXCollections.observableArrayList(appService.getClubCheckInsForTheMonthEmployees(currentClub.getId(),fechaMesAño));
        gananciasLabel.setText("Ganancias totales: " +costs.getTotal());
        for(List check : checks){
            HBox checkInBox = new HBox(10);
            String fecha = check.get(0).toString();
            Label fechaLabel = new Label("Fecha: " + fecha);
            Label diaLabel = new Label("Dia: " + check.get(1));
            Label horaInicio= new Label("Hora inicio:" + check.get(2));
            fechaLabel.setStyle("-fx-font-weight: bold");
            fechaLabel.setFont(new Font("Arial", 14));
            diaLabel.setStyle("-fx-font-weight: bold");
            diaLabel.setFont(new Font("Arial", 14));
            horaInicio.setStyle("-fx-font-weight: bold");
            horaInicio.setFont(new Font("Arial", 14));
            HBox.setHgrow(fechaLabel, Priority.ALWAYS);
            HBox.setHgrow(diaLabel, Priority.ALWAYS);
            checkInBox.getChildren().addAll(fechaLabel,diaLabel,horaInicio);
            costsBox.getChildren().add(checkInBox);
        }
    }

    @FXML
    protected void setEmployeeTable(){
        employeeTable.getItems().clear();
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        String now = LocalDate.now().toString();
        Scanner scanner = new Scanner(now);
        scanner.useDelimiter("-");
        String fechaMesAño = scanner.next()+"-"+ scanner.next();
        ObservableList<Employee> empleados = FXCollections.observableArrayList(appService.getClubCheckInsForTheMonthEmployees(currentClub.getId(),fechaMesAño));
        employeeTable.setItems(empleados);
    }
}
