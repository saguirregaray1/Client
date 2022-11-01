package com.example.clientTIC.ui;

import com.example.clientTIC.models.Activity;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class setTimesActivityController {

    @FXML
    public VBox timesBox;

    @FXML
    private TextField dayField;

    @FXML
    protected void setHorariosInBox(){
        dayField.getText();
        HBox hbox = new HBox(10);
        timesBox.getChildren().add(hbox);


    }
}
