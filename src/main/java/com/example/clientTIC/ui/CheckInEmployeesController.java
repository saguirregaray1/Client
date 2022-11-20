package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
import com.example.clientTIC.models.*;
import com.example.clientTIC.spring.AppService;
import com.example.clientTIC.spring.ApplicationContextProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CheckInEmployeesController implements Initializable {

    private Company currentCompany;

    public void setCurrentCompany(Company currentCompany) {
        this.currentCompany = currentCompany;
    }

    @FXML
    private VBox costsBox;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee,Long> idColumn;

    @FXML
    private Label costosCompania;

    @FXML
    protected void volver(ActionEvent event) throws IOException {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
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
        Long costos = appService.getTotalCompanyCostsForTheMonth(currentCompany.getId(), fechaMesAño).getTotal();
        costosCompania.setText("Costo total: " + costos);
    }

    void setCosts(){
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        String now = LocalDate.now().toString();
        Scanner scanner = new Scanner(now);
        scanner.useDelimiter("-");
        String fechaMesAño = scanner.next()+"-"+ scanner.next();
        Costs costs = appService.getTotalCompanyCostsForTheMonth(currentCompany.getId(),fechaMesAño);
        List<CheckIn> checkIns= costs.getCheckIns();
        for(CheckIn check : checkIns){
            HBox checkInBox = new HBox(10);
            String fecha = check.getFecha();
            Label fechaLabel = new Label("Fecha: " + fecha);
            Label cedula = new Label("Dia: " + check.getEmployee().getCedula());
            Label saldo= new Label("Hora inicio:" + check.getEmployee().getSaldo());
            fechaLabel.setStyle("-fx-font-weight: bold");
            fechaLabel.setFont(new Font("Arial", 14));
            cedula.setStyle("-fx-font-weight: bold");
            cedula.setFont(new Font("Arial", 14));
            saldo.setStyle("-fx-font-weight: bold");
            saldo.setFont(new Font("Arial", 14));
            HBox.setHgrow(fechaLabel, Priority.ALWAYS);
            HBox.setHgrow(cedula, Priority.ALWAYS);
            checkInBox.getChildren().addAll(fechaLabel,cedula,saldo);
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
        Costs costs =appService.getCompanyCostsForTheMonth(currentCompany.getId(), fechaMesAño);
        ObservableList<Employee> empleados = FXCollections.observableArrayList(costs.getUsers());
        employeeTable.setItems(empleados);
    }
}


