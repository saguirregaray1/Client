package com.example.clientTIC.ui;

import com.example.clientTIC.Activity;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserViewController extends ListView<Activity> implements Initializable {


    @FXML
    private TextField filter;

    @FXML
    private VBox box;

    @FXML
    private ListView<Activity> activityListView;

    @FXML
    void searchClubs(ActionEvent event2) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClubsListView.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)event2.getSource()).getScene().getWindow();
        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void setFilter(){
        filter.getText();
    }

    @FXML
    protected void registerToActivity(){
        //registrarse a actividad
    }


    //Estoy intentando ver como insertar un boton en cada fila de la lista que funcione para elimiar
    //todavia no se si funciona pero es una primera idea


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        activityListView.setCellFactory(new Callback<ListView<Activity>, ListCell<Activity>>() {
            @Override
            public ListCell<Activity> call(ListView<Activity> param) {
                return new ButtonCell();
            }
        });
    }
    static class ButtonCell extends ListCell<Activity>{
        @Override
        public void updateItem(Activity activity, boolean empty){
            super.updateItem(activity, empty);
            if (activity != null) {
                final Button btn = new Button(activity + " with index " + getIndex());
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        getListView().getItems().remove(getItem());
                        //eliminar actividad de la bdd
                    }
                });
                setGraphic(btn);
            } else {
                setGraphic(null);
            }

        }
    }

}
