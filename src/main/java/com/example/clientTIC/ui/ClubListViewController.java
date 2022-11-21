package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
import com.example.clientTIC.models.*;
import com.example.clientTIC.spring.AppService;
import com.example.clientTIC.spring.ApplicationContextProvider;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
    private ChoiceBox<String> activitiesBox;

    @FXML
    private ChoiceBox<String> imagesActivityBox;


    @FXML
    private ChoiceBox<ActivityCategories> categoriesBox;


    ObservableList<ActivityCategories> categorias = FXCollections.observableArrayList(ActivityCategories.Deportes_En_Equipo,
            ActivityCategories.Piscina,
            ActivityCategories.Gimnasio,
            ActivityCategories.Clases);
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
    private ChoiceBox<String> horarioInicio;

    @FXML
    private ChoiceBox<String> horarioFin;

    @FXML
    private TextField cuposLunes;

    private List<File> imagenes;

    @FXML
    private CheckBox habilitarCupos;

    @FXML
    private ChoiceBox<String> dias;

    @FXML
    private Label notificationLabelImages;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("volver.png");
        ImageView img = new ImageView(image);
        img.setFitHeight(100);
        img.setFitWidth(200);
        returnButton.setGraphic(img);
        habilitarCupos.setSelected(true);
        categoriesBox.getItems().addAll(categorias);
        setListOfActivities();
        dias.getItems().addAll("MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY","SUNDAY");
        horarioInicio.getItems().addAll("00:00:00", "01:00:00", "02:00:00","03:00:00","04:00:00","05:00:00","06:00:00",
                "07:00:00","08:00:00","09:00:00","10:00:00","11:00:00","12:00:00","13:00:00","14:00:00","15:00:00","16:00:00",
                "17:00:00","18:00:00","19:00:00","20:00:00","21:00:00","22:00:00","23:00:00");
        horarioFin.getItems().addAll("00:00:00", "01:00:00", "02:00:00","03:00:00","04:00:00","05:00:00","06:00:00",
                "07:00:00","08:00:00","09:00:00","10:00:00","11:00:00","12:00:00","13:00:00","14:00:00","15:00:00","16:00:00",
                "17:00:00","18:00:00","19:00:00","20:00:00","21:00:00","22:00:00","23:00:00");
    }

    private void setListOfActivities(){
        activitiesBox.getItems().clear();
        imagesActivityBox.getItems().clear();
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        List<List> activityList = appService.getListOfActivitiesByClub(appUser.getClub().getId());

        // select a.club,a.nombre,a.cupos,a.precio,a.activityCategories,c.nombre,c.dir
        List<String> activitiesClub = new ArrayList<>() {
        };

        for (List value: activityList){
            activitiesClub.add(value.get(0).toString());
        }
        ObservableList<String> activityCLubNames = FXCollections.observableArrayList(activitiesClub);
        activitiesBox.getItems().addAll(activityCLubNames);
        imagesActivityBox.getItems().addAll(activityCLubNames);
    }

    @FXML
    protected void crearHorarios(ActionEvent event) throws IOException {
        String dia = dias.getValue();
        String inicio = horarioInicio.getValue();
        String finale = horarioFin.getValue();
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
            visualizarHorarios(dia,inicio,finale,cupos);
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
            notificationLabel.setText(dia+ " de " + start +" a "+finale + " agregado");
        }else{
            HBox box = new HBox(10);
            box.getChildren().addAll(day,inicio,finish,cupos);
            horariosCreateActivity.getChildren().add(box);
            notificationLabel.setText(dia+ " de " + start +" a " +finale + " agregado");
        }
    }

    @FXML
    protected void borrarHorarios(){
        horariosCreateActivity.getChildren().clear();
        notificationLabel.setText("");
        horariosIngresados = null;
    }



    @FXML
    protected void createActivityButton(){
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        String nombreActividad = createActivityNameCupos.getText();
        List<Quota> cuposActividad = horariosIngresados;
        Long precio = Long.valueOf(activityPrice.getText());
        Activity actividad = new Activity(appUser.getClub(),nombreActividad,precio,cuposActividad,categoriesBox.getValue());
        // label
        appService.addNewActivity(appUser.getClub(),nombreActividad,precio,cuposActividad,categoriesBox.getValue());
        horariosCreateActivity.getChildren().clear();
        horariosIngresados.clear();
        notificationLabel.setText("");
        setListOfActivities();
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
            if (imagenes == null){
                imagenes = new ArrayList<>();
            }
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
        Activity actividad = appService.getActivityByNombre(appUser.getClub().getId(),imagesActivityBox.getValue());
        for(File archivo : imagenes){
            appService.uploadActivityPicture(archivo,actividad.getId());
        }
        notificationLabelImages.setText("Imagenes cargadas correctamente");
        imagenes.clear();
    }
    @FXML
    protected void registerUserButton(){
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        String email = registerUserEmail.getText();
        String password = registerUserPassword.getText();
        // label
        HttpResponse<JsonNode> response=appService.addNewClubUser(email,password,appUser.getClub().getId());
        if (response.getStatus()==200){
            notificationLabelTAB2.setText(email+ " registrado correctamente");
            //registrado correctamente
        }
        else{
            notificationLabelTAB2.setText("No se pudo registrar al usuario");
        }

    }

    @FXML
    protected void deleteUserButton(){
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        String email = deleteUserID.getText();
        HttpResponse<JsonNode> response = appService.deleteClubUser(email);
        if (response.getStatus()==200){
            notificationLabelTAB2.setText(email+ " borrado correctamente");
        }
        else{
            notificationLabelTAB2.setText("Usuario no encontrado");

        }
    }

    @FXML
    protected void verHorarios() throws Exception {
        AppService appService= ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
        String nombreActividad = activitiesBox.getValue();
        Activity currentActivity = appService.getActivityByNombre(appUser.getClub().getId(),nombreActividad);
        List<Quota> cupos= currentActivity.getCupos();
        if (cupos != null) {
            horariosBox.getChildren().clear();
            for (Quota cupo : cupos) {
                if (cupo.getDay().equals(DayOfWeek.from(LocalDate.now()).toString())){
                HBox cuposBox = new HBox(10);
                Label dayName = new Label(cupo.getDay());
                String inicio = cupo.getStartTime();
                String fin = cupo.getFinishTime();
                Label horario= new Label(inicio + " : "+ fin);
                Button reserveButton = new Button("Check-In");
                reserveButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            //fixme cedula
                            HttpResponse<JsonNode> response= appService.checkInWithReservation(Long.valueOf(userCheckIn.getText()),cupo.getStartTime(),currentActivity.getId());
                            if (response.getStatus()==200){
                                notificationLabelTAB1.setText("Check-In realizado correctamente");
                            }
                            else{
                                notificationLabelTAB1.setText("Check-In no realizado");}
                        }
                });

                cuposBox.getChildren().addAll(dayName,horario,reserveButton);
                horariosBox.getChildren().add(cuposBox);
            }}
        }else{notificationLabel.setText("No existe la actividad");}


    }

    @FXML
    protected void verCheckIns(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(CheckInEmployeesController.class.getResource("CheckInClub.fxml"));
        CheckInClubController checkInClubController = new CheckInClubController();
        checkInClubController.setCurrentClub(appUser.getClub());
        checkInClubController.setAppUser(appUser);
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
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }






}
