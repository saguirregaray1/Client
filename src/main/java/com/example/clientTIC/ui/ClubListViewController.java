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
    private TextField martesInicio;

    @FXML
    private TextField martesFin;

    @FXML
    private TextField miercolesInicio;

    @FXML
    private TextField miercolesFin;

    @FXML
    private TextField juevesInicio;

    @FXML
    private TextField juevesFin;

    @FXML
    private TextField viernesInicio;

    @FXML
    private TextField viernesFin;

    @FXML
    private TextField sabadoInicio;

    @FXML
    private TextField sabadoFin;

    @FXML
    private TextField domingoInicio;

    @FXML
    private TextField domingoFin;

    @FXML
    private TextField cuposLunes;

    @FXML
    private TextField cuposMartes;

    @FXML
    private TextField cuposMiercoles;

    @FXML
    private TextField cuposJueves;

    @FXML
    private TextField cuposViernes;

    @FXML
    private TextField cuposSabado;

    @FXML
    private TextField cuposDomingo;

    @FXML
    private CheckBox lunesHorarios;

    @FXML
    private CheckBox martesHorarios;

    @FXML
    private CheckBox miercolesHorarios;

    @FXML
    private CheckBox juevesHorarios;

    @FXML
    private CheckBox viernesHorarios;

    @FXML
    private CheckBox sabadoHorarios;

    @FXML
    private CheckBox domingosHorarios;

    @FXML
    private CheckBox habilitarCupos;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("volver.png");
        ImageView img = new ImageView(image);
        returnButton.setGraphic(img);
        lunesHorarios.setSelected(true);
        martesHorarios.setSelected(true);
        miercolesHorarios.setSelected(true);
        juevesHorarios.setSelected(true);
        viernesHorarios.setSelected(true);
        sabadoHorarios.setSelected(true);
        domingosHorarios.setSelected(true);
        habilitarCupos.setSelected(true);
    }

    @FXML
    protected void crearHorarios(ActionEvent event) throws IOException {

    }

    @FXML
    protected void createActivityButtonCupos(){
        String nombreActividad = createActivityName.getText();


    }

    @FXML
    protected void lunesHoraries(){
        if (lunesHorarios.isSelected()){
            lunesInicio.setVisible(true);
            lunesFin.setVisible(true);
            lunesInicio.setEditable(true);
            lunesFin.setEditable(true);
        }else{
            lunesInicio.setVisible(false);
            lunesFin.setVisible(false);
            lunesInicio.setEditable(false);
            lunesFin.setEditable(false);
        }
    }

    @FXML
    protected void martesHoraries(){
        if (martesHorarios.isSelected()){
            martesInicio.setVisible(true);
            martesFin.setVisible(true);
            martesInicio.setEditable(true);
            martesFin.setEditable(true);
        }else{
            martesInicio.setVisible(false);
            martesFin.setVisible(false);
            martesInicio.setEditable(false);
            martesFin.setEditable(false);
        }
    }

    @FXML
    protected void miercolesHoraries(){
        if (miercolesHorarios.isSelected()){
            miercolesInicio.setVisible(true);
            miercolesFin.setVisible(true);
            miercolesInicio.setEditable(true);
            miercolesFin.setEditable(true);
        }else{
            miercolesInicio.setVisible(false);
            miercolesFin.setVisible(false);
            miercolesInicio.setEditable(false);
            miercolesFin.setEditable(false);
        }
    }

    @FXML
    protected void juevesHoraries(){
        if (juevesHorarios.isSelected()){
            juevesInicio.setVisible(true);
            juevesFin.setVisible(true);
            juevesInicio.setEditable(true);
            juevesFin.setEditable(true);
        }else{
            juevesInicio.setVisible(false);
            juevesFin.setVisible(false);
            juevesInicio.setEditable(false);
            juevesFin.setEditable(false);
        }
    }

    @FXML
    protected void viernesHoraries(){
        if (viernesHorarios.isSelected()){
            viernesInicio.setVisible(true);
            viernesFin.setVisible(true);
            viernesInicio.setEditable(true);
            viernesFin.setEditable(true);
        }else{
            viernesInicio.setVisible(false);
            viernesFin.setVisible(false);
            viernesInicio.setEditable(false);
            viernesFin.setEditable(false);
        }
    }

    @FXML
    protected void sabadoHoraries(){
        if (sabadoHorarios.isSelected()){
            sabadoInicio.setVisible(true);
            sabadoFin.setVisible(true);
            sabadoInicio.setEditable(true);
            sabadoFin.setEditable(true);
        }else{
            sabadoInicio.setVisible(false);
            sabadoFin.setVisible(false);
            sabadoInicio.setEditable(false);
            sabadoFin.setEditable(false);
        }
    }

    @FXML
    protected void domingoHoraries(){
        if (domingosHorarios.isSelected()){
            domingoInicio.setVisible(true);
            domingoFin.setVisible(true);
            domingoInicio.setEditable(true);
            domingoFin.setEditable(true);
        }else{
            domingoInicio.setVisible(false);
            domingoFin.setVisible(false);
            domingoInicio.setEditable(false);
            domingoFin.setEditable(false);
        }
    }

    @FXML
    protected void tieneCupos(){
        if (habilitarCupos.isSelected()){
            cuposLunes.setVisible(true);
            cuposLunes.setEditable(true);
            cuposMartes.setVisible(true);
            cuposMartes.setEditable(true);
            cuposMiercoles.setEditable(true);
            cuposMiercoles.setVisible(true);
            cuposJueves.setEditable(true);
            cuposJueves.setVisible(true);
            cuposViernes.setEditable(true);
            cuposViernes.setVisible(true);
            cuposSabado.setEditable(true);
            cuposSabado.setVisible(true);
            cuposDomingo.setEditable(true);
            cuposDomingo.setEditable(true);
        }else{
            cuposLunes.setVisible(false);
            cuposLunes.setEditable(false);
            cuposMartes.setVisible(false);
            cuposMartes.setEditable(false);
            cuposMiercoles.setEditable(false);
            cuposMiercoles.setVisible(false);
            cuposJueves.setEditable(false);
            cuposJueves.setVisible(false);
            cuposViernes.setEditable(false);
            cuposViernes.setVisible(false);
            cuposSabado.setEditable(false);
            cuposSabado.setVisible(false);
            cuposDomingo.setEditable(false);
            cuposDomingo.setEditable(false);

        }
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











}
