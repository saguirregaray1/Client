package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class ClubListViewController implements Initializable {

    public AppUser appUser;

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }


    @FXML
    private VBox horariosBox;

    @FXML
    private Button returnButton;

    @FXML
    private TextField userCheckIn;

    @FXML
    private Label checkInResult;

    @FXML
    private VBox imagesActivity;

    @FXML
    private TextField createActivityName;

    @FXML
    private DatePicker dateActivity;

    @FXML
    private TextField deleteUserID;
    @FXML
    private  TextField registerUserEmail;

    @FXML
    private TextField registerUserPassword;

    @FXML
    private  TextField companyNameEmployee;

    @FXML
    private TextField activityNameCheck;

    @FXML
    private TextField lunesInicio;

    @FXML
    private TextField lunesFin;

    @FXML
    private TextField cuposLunes;


    @FXML
    private CheckBox habilitarCupos;

    @FXML
    private ChoiceBox<String> dias;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("volver.png");
        ImageView img = new ImageView(image);
        img.setFitHeight(100);
        img.setFitWidth(200);
        returnButton.setGraphic(img);
        habilitarCupos.setSelected(true);
        dias.getItems().addAll("Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo");
    }

    @FXML
    protected void crearHorarios(ActionEvent event) throws IOException {
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        String dia = dias.getValue();
        String inicio = lunesInicio.getText();
        String finale = lunesFin.getText();
        int cupos = Integer.MAX_VALUE;
        if (habilitarCupos.isSelected()){
            cupos = Integer.parseInt(cuposLunes.getText());
        }
        Quota horario = new Quota(dia,inicio,finale,cupos);
    }

    @FXML
    protected void createActivityButtonCupos(){
        String nombreActividad = createActivityName.getText();


    }

    @FXML
    protected void tieneCupos(){
        if (habilitarCupos.isSelected()){
            cuposLunes.setVisible(true);
            cuposLunes.setEditable(true);
        }else{
            cuposLunes.setVisible(false);
            cuposLunes.setEditable(false);
        }
    }


    @FXML
    protected void chargeImagesButton(ActionEvent event){
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(((Node) event.getTarget()).getScene().getWindow());
        if (file != null) {
            Image image1 = new Image(file.toURI().toString());
            ImageView ip = new ImageView(image1);
            ip.setFitHeight(100);
            ip.setFitWidth(200);
            imagesActivity.getChildren().add(ip);
            }
    }

    @FXML
    protected void registerUserButton(){
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        String email = registerUserEmail.getText();
        String password = registerUserPassword.getText();
        String companyName = companyNameEmployee.getText();
        appService.addNewClubUser(email,password,appUser.getClub().getId()
        );
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
        // if (necesitaReserva){
        Long id = Long.valueOf(userCheckIn.getText());
        //fixme
        appService.cameToActivity(id,1L);

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

        String fecha = String.valueOf(LocalDateTime.now());
    }

    @FXML
    protected void verHorarios() throws Exception {
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        String nombreActividad = activityNameCheck.getText();
        List<List> listaActividades = appService.getListOfActivities();
        List<Quota> cupos= null;
        for (List value : listaActividades) {
            Activity activity = new Activity((String) value.get(0), Long.valueOf(value.get(1).toString()), ActivityCategories.valueOf((String) value.get(2)));
            if (activity.getNombre() == nombreActividad){
               cupos =appService.getActivityQuota(activity.getId());
            }
        }
        if (cupos != null) {
            for (Quota cupo : cupos) {
                horariosBox.getChildren().clear();
                HBox cuposBox = new HBox(10);
                Label dayName = new Label(cupo.getDay());
                String inicio = cupo.getStartTime();
                String fin = cupo.getFinishTime();
                Label horario= new Label(inicio + " : "+ fin);
                Button reserveButton = new Button("Reservar");
                reserveButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //reservar en el horario especifico
                    }
                });
                cuposBox.getChildren().addAll(dayName,horario,reserveButton);
                horariosBox.getChildren().add(cuposBox);
            }
        }else{throw new Exception("No existe la actividad");}


    }




    @FXML
    protected void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LogInScreen.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }






}
