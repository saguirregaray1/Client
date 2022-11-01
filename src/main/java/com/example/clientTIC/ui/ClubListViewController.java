package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
import com.example.clientTIC.models.Company;
import com.example.clientTIC.models.Employee;
import com.example.clientTIC.spring.AppService;
import com.example.clientTIC.spring.ApplicationContextProvider;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ClubListViewController implements Initializable {

    public AppUser appUser;

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @FXML
    private Button returnButton;

    @FXML
    private TextField userCheckIn;

    @FXML
    private Label checkInResult;

    @FXML
    private VBox imagesActivity;

    @FXML
    private VBox horariosActivity;

    @FXML
    private TextField createActivityNameCupos;

    @FXML
    private TextField cuposActivity;

    @FXML
    private DatePicker dateActivity;

    @FXML
    private TextField deleteUserID;

    @FXML
    private TextField registerUserID;

    @FXML
    private  TextField registerUserEmail;

    @FXML
    private  TextField companyNameEmployee;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("volver.png");
        ImageView img = new ImageView(image);
        returnButton.setGraphic(img);
    }

    @FXML
    protected void createActivityButtonCupos(){
        String nombreActividad = createActivityNameCupos.getText();
        int cupos = Integer.parseInt(cuposActivity.getText());
        String date = String.valueOf(dateActivity.getValue());

    }

    @FXML
    protected void createActivitywithSchedule(){

    }

    @FXML
    protected void chargeImagesButton(ActionEvent event){
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(((Node) event.getTarget()).getScene().getWindow());
        if (file != null) {
            Image image1 = new Image(file.toURI().toString());
            ImageView ip = new ImageView(image1);
            imagesActivity.getChildren().add(ip);
            }
    }

    @FXML
    protected void registerUserButton(){
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        Long cedula = Long.valueOf(registerUserID.getText());
        String email = registerUserEmail.getText();
        String companyName = companyNameEmployee.getText();
        ObservableList<Company> listOfCompany = appService.getListOfCompanies();
        for (Company company: listOfCompany){
            if (company.getNombre() == companyName){

            }
        }
    }

    @FXML
    protected void deleteUserButton(){
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        String cedula = deleteUserID.getText();
        appService.deleteEmployees(cedula);
    }

    @FXML
    protected void verifyButton(){
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        Long id = Long.valueOf(userCheckIn.getText());
        ObservableList<Employee> employees= appService.getListOfEmployees();
        Boolean encontrado = false;
        for (Employee employee: employees){
            if (employee.getId() == id){
                checkInResult.setText("El usuario se encuentra registrado");
                encontrado = true;
            }
        }
        if (encontrado==false){
            checkInResult.setText("El usuario no se encuentra registrado en el club");
        }
    }


    @FXML
    protected void tieneHorarios(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("setTimesActivity.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }








}
