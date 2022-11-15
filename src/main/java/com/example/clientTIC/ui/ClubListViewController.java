package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
import com.example.clientTIC.models.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClubListViewController implements Initializable {

    public AppUser appUser;

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    private List<Quota> horariosIngresados;

    @FXML
    private TextField activityPrice;

    @FXML
    private CheckBox checkInTieneReservas;

    @FXML
    private VBox horariosBox;

    @FXML
    private TextField addImageActivity;

    @FXML
    private Button returnButton;

    @FXML
    private TextField userCheckIn;

    @FXML
    private Label checkInResult;

    @FXML
    private VBox imagesActivity;

    @FXML
    private VBox horariosCreateActivity;

    @FXML
    private ImageView imagePrev;

    @FXML
    private TextField createActivityName;

    @FXML
    private Label notificationLabel;

    @FXML
    private TextField deleteUserID;

    @FXML
    private TextField createActivityNameCupos;
    @FXML
    private  TextField registerUserEmail;

    @FXML
    private TextField registerUserPassword;

    @FXML
    private  TextField companyNameEmployee;

    @FXML
    private TextField activityNameCheck;

    @FXML
    private Label notificationLabelTAB1;

    @FXML
    private Label notificationLabelTAB2;

    @FXML
    private TextField lunesInicio;

    @FXML
    private TextField lunesFin;

    @FXML
    private TextField cuposLunes;

    private List<File> imagenes;

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
        String dia = dias.getValue();
        String inicio = lunesInicio.getText();
        String finale = lunesFin.getText();
        int cupos = 0;
        Quota horario = null;
        if (habilitarCupos.isSelected()){
            cupos = Integer.parseInt(cuposLunes.getText());
            horario = new Quota(dia,inicio,finale,cupos);
        }else {
            horario = new Quota(dia, inicio, finale);
        }
        if (horariosIngresados == null){
            horariosIngresados= new ArrayList<Quota>();
            horariosIngresados.add(horario);
            visualizarHorarios(dia,inicio,finale,cupos);
        }else{
            this.horariosIngresados.add(horario);
        }
    }

    private void visualizarHorarios(String dia, String start, String finale, int cupes){
        Label day = new Label(dia);
        Label inicio = new Label(start);
        Label finish = new Label(finale);
        Label cupos = new Label(String.valueOf(cupes));
        if (cupes==0){
            HBox box = new HBox(10);
            box.getChildren().addAll(day,inicio,finish);
            horariosCreateActivity.getChildren().add(box);
        }else{
            HBox box = new HBox(10);
            box.getChildren().addAll(day,inicio,finish,cupos);
            horariosCreateActivity.getChildren().add(box);
        }
    }



    @FXML
    protected void createActivityButtonCupos(){
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        String nombreActividad = createActivityNameCupos.getText();
        List<Quota> cuposActividad = horariosIngresados;
        Long precio = Long.valueOf(activityPrice.getText());
        Activity actividad = new Activity(appUser.getClub(),nombreActividad,precio,cuposActividad,ActivityCategories.CATEGORY_1);
        // label
        appService.addNewActivity(appUser.getClub(),nombreActividad,precio,cuposActividad,ActivityCategories.CATEGORY_1);
        horariosCreateActivity.getChildren().clear();
        horariosIngresados.clear();
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
        final FileChooser f = new FileChooser();
        File file = f.showOpenDialog(((Node) event.getTarget()).getScene().getWindow());
        if (file != null) {
            imagenes.add(file);
            Image image1 = new Image(file.toURI().toString());
            ImageView ip = new ImageView(image1);
            ip.setFitHeight(100);
            ip.setFitWidth(200);
            imagesActivity.getChildren().add(ip);
            imagePrev.setImage(image1);
            }
    }

    @FXML
    protected void insertImages(ActionEvent event){
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        Activity actividad = appService.getActivityByNombre(appUser.getClub().getId(),addImageActivity.getText());
        for(File archivo : imagenes){
            appService.uploadActivityPicture(archivo,actividad.getId());
        }
        imagenes.clear();
    }
    @FXML
    protected void registerUserButton(){
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        String email = registerUserEmail.getText();
        String password = registerUserPassword.getText();
        String companyName = companyNameEmployee.getText();
        // label
        HttpResponse<JsonNode> response=appService.addNewClubUser(email,password,appUser.getClub().getId());
        if (response.getStatus()==200){
            notificationLabelTAB2.setText("Registrado correctamente");
            //registrado correctamente
        }
        else{
            notificationLabelTAB2.setText(response.getBody().toString());
        }

    }

    @FXML
    protected void deleteUserButton(){
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        String email = deleteUserID.getText();
        appService.deleteClubUser(email);
    }

    /*FXML
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
    }*/

    @FXML
    protected void verHorarios() throws Exception {
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        String nombreActividad = activityNameCheck.getText();
        Activity currentActivity = appService.getActivityByNombre(appUser.getClub().getId(),nombreActividad);
        List<Quota> cupos= currentActivity.getCupos();
        if (cupos != null) {
            horariosBox.getChildren().clear();
            for (Quota cupo : cupos) {
                HBox cuposBox = new HBox(10);
                Label dayName = new Label(cupo.getDay());
                String inicio = cupo.getStartTime();
                String fin = cupo.getFinishTime();
                Label horario= new Label(inicio + " : "+ fin);
                Button reserveButton = new Button("Check In");
                reserveButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            //cedula
                            HttpResponse<JsonNode> response= appService.checkInWithReservation(1234L,cupo.getStartTime(),currentActivity.getId());
                            if (response.getStatus()==200){
                                notificationLabelTAB1.setText("Check In realizado correctamente");
                                //registrado correctamente
                            }
                            else{
                                notificationLabelTAB1.setText(response.getBody().toString());}
                        }
                });

                cuposBox.getChildren().addAll(dayName,horario,reserveButton);
                horariosBox.getChildren().add(cuposBox);
            }
        }else{notificationLabel.setText("No existe la actividad");}


    }

    @FXML
    protected void verCheckIns(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(CheckInActivityController.class.getResource("CheckInClub.fxml"));
        CheckInClubController checkInClubController = new CheckInClubController();
        checkInClubController.setCurrentClub(appUser.getClub());
        loader.setController(checkInClubController);
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
