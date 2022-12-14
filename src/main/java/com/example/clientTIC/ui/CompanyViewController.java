package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
import com.example.clientTIC.models.Costs;
import com.example.clientTIC.models.Employee;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CompanyViewController implements Initializable {

    public AppUser appuser;

    @FXML
    private TextField idField;
    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField saldoField;

    @FXML
    private Label notificationLabel;

    @FXML
    private VBox employeeList;

    @FXML
    private Button returnButton;

    public void setAppuser(AppUser appuser) {
        this.appuser = appuser;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("volver.png");
        ImageView img = new ImageView(image);
        img.setFitHeight(100);
        img.setFitWidth(150);
        returnButton.setGraphic(img);
       // setListOfEmployees();
    }

    public void setListOfEmployees(){
        employeeList.getChildren().clear();
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
         ObservableList<Employee> employees = appService.getListOfCompanyEmployees(appuser.getCompany().getId());
  //      ObservableList<Employee> employees = appService.getListOfEmployees();
        for(Employee employee : employees){
            HBox employeeBox = new HBox(30);
            Label idLabel = new Label("C.I: " + String.valueOf(employee.getCedula()));
            Label saldoLabel = new Label("Saldo: " + String.valueOf(employee.getSaldo()));
            Button borrar = new Button("Borrar empleado");
            borrar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    appService.deleteEmployees(String.valueOf(employee.getCedula()));
                    employeeList.getChildren().remove(employeeBox);
                }
            });
            idLabel.setStyle("-fx-font-weight: bold");
            idLabel.setFont(new Font("Arial", 16));
            saldoLabel.setStyle("-fx-font-weight: bold");
            saldoLabel.setFont(new Font("Arial", 16));
            HBox.setHgrow(idLabel, Priority.ALWAYS);
            HBox.setHgrow(saldoLabel, Priority.ALWAYS);
            employeeBox.getChildren().addAll(idLabel,saldoLabel,borrar);
            employeeList.getChildren().add(employeeBox);
        }
    }

    @FXML
    protected void addEmployee(){
        Long cedula = Long.valueOf(idField.getText());
        Long saldo = Long.valueOf(saldoField.getText());
        String email = emailField.getText();
        String password= passwordField.getText();
        AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        // label
        HttpResponse<JsonNode> response=appService.addNewEmployee(cedula,this.appuser.getCompany(),saldo,email,password);
        if (response.getStatus()==200){
            notificationLabel.setText("Registrado correctamente");
            //registrado correctamente
        }
        else{
            notificationLabel.setText("No se pudo registrar al empleado");
        }
        setListOfEmployees();

    }

    @FXML
    protected void volver(ActionEvent event) throws IOException {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void verCostosCompania(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(CheckInEmployeesController.class.getResource("CheckInEmployees.fxml"));
        CheckInEmployeesController checkInEmployeesController = new CheckInEmployeesController();
        checkInEmployeesController.setCurrentCompany(appuser.getCompany());
        loader.setController(checkInEmployeesController);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
