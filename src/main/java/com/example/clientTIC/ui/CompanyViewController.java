package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
import com.example.clientTIC.models.Costs;
import com.example.clientTIC.models.Employee;
import com.example.clientTIC.spring.AppService;
import com.example.clientTIC.spring.ApplicationContextProvider;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import java.util.ResourceBundle;

public class CompanyViewController implements Initializable {

    public AppUser appuser;

    @FXML
    private TextField idField;

    @FXML
    private Label costosCompania;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField saldoField;

    @FXML
    private Label registeredLabel;

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
            idLabel.setFont(new Font("Arial", 20));
            saldoLabel.setStyle("-fx-font-weight: bold");
            saldoLabel.setFont(new Font("Arial", 20));
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
        appService.addNewEmployee(cedula,this.appuser.getCompany(),saldo,email,password);
        registeredLabel.setText("Usuario registrado");
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
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        //fixme
        Long costos = appService.getTotalCompanyCostsForTheMonth(appuser.getCompany().getId(),"").getTotal();
        costosCompania.setText("Costo total: " + costos);
    }
}
