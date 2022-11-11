package com.example.clientTIC.ui;

import com.example.clientTIC.AppUser;
import com.example.clientTIC.models.CheckIn;
import com.example.clientTIC.models.Club;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckInListController implements Initializable {

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
