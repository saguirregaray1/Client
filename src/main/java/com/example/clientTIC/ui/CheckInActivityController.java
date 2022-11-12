package com.example.clientTIC.ui;

import com.example.clientTIC.models.CheckIn;
import com.example.clientTIC.models.Club;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckInActivityController implements Initializable {

    public Club club;

    public void setClub(Club club) {
        this.club = club;
    }

    @FXML
    private TableView<CheckIn> checkInTableView;

    @FXML
    private TableColumn<CheckIn,String> activityNameColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
