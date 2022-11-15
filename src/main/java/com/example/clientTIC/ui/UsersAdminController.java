package com.example.clientTIC.ui;

import com.example.clientTIC.models.Company;
import com.example.clientTIC.models.Employee;
import com.example.clientTIC.spring.AppService;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.clientTIC.spring.ApplicationContextProvider.getApplicationContext;

public class UsersAdminController implements Initializable {

    @FXML
    private TextField createUserName;

    @FXML
    private TextField createUserCompany;

    @FXML
    private TextField createUserEmail;

    @FXML
    private TextField createUserPassword;


    @FXML
    private TextField deleteUserName;

    @FXML
    private VBox UserList;

    @FXML
    private Label notificationLabel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showUsers();
    }

    @FXML
    protected void crearUser(){
        Long nombre = Long.valueOf(createUserName.getText());
        String compania = createUserCompany.getText();
        String email = createUserEmail.getText();
        Long saldo = 1000L;
        String contraseña = createUserPassword.getText();
        Company currentCompany = null;
        AppService appService=getApplicationContext().getBean(AppService.class);
        List<Company> companias = appService.getListOfCompanies();
        for(Company comp : companias){
            if (comp.getNombre().equals(compania)){
                currentCompany=comp;
            }
        } // label
        HttpResponse<JsonNode> response = appService.addNewEmployee(nombre,currentCompany,saldo,email,contraseña);
        if (response.getStatus()==200){
            notificationLabel.setText("Registrado correctamente");
            //registrado correctamente
        }
        else{
            notificationLabel.setText(response.getBody().toString());
        }
        showUsers();
    }

    @FXML
    protected void borrarUser(){
        String nombre = deleteUserName.getText();
        AppService appService=getApplicationContext().getBean(AppService.class);
        appService.deleteEmployees(nombre);
        showUsers();
    }

    @FXML
    protected void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void showUsers(){
        UserList.getChildren().clear();
        AppService appService=getApplicationContext().getBean(AppService.class);
        ObservableList<Employee> users =appService.getListOfEmployees();
        for(Employee employee : users){
            HBox hBox = new HBox(10);
            Button verCheckIn = new Button("Ver CheckIn");
            Label nombreLabel = new Label("C.I: " +employee.getId());
            Label dirLabel= new Label("Saldo: "+ employee.getSaldo());
            verCheckIn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    FXMLLoader loader = new FXMLLoader(CheckInActivityController.class.getResource("UserInfo.fxml"));
                    UserInfoController userInfoController = new UserInfoController();
                    userInfoController.setAppUser(employee.getAppUser());
                    loader.setController(userInfoController);
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
            });
            hBox.getChildren().addAll(nombreLabel,dirLabel,verCheckIn);
            UserList.getChildren().add(hBox);
        }
    }
}
