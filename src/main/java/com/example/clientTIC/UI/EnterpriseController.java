package com.example.clientTIC.UI;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class EnterpriseController implements Initializable {

    @FXML
    private TextField nameEnterprise;

    @FXML
    private TextField emailEnterprise;

    @FXML
    private TextField passwordEnterprise;

    @FXML
    private TableView<Enterprise> EnterprisesTable;

    @FXML
    private TableColumn<Enterprise,String> nameColumn;

    @FXML
    private TableColumn<Enterprise,String> emailColumn;

    @FXML
    private TableColumn<Enterprise, PasswordField> passwordColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        //add your data to the table here.
        //EnterprisesTable.setItems(enterpriseModels);
    }

    final ObservableList<Enterprise> enterpriseModels = FXCollections.observableArrayList(
            new Enterprise("Pepsi","pepsico@gmail.com", "Chepchieng"),
            new Enterprise("Arcor","arcor@gmail.com", "Tooeeedd")
            );
    @FXML
    protected void addEnterprise(){

        String name = nameEnterprise.getText();
        String email = emailEnterprise.getText();
        String password = passwordEnterprise.getText();

        if(name.equals(null)|| email.equals(null)||password.equals(null) || name.equals("")|| email.equals("")||password.equals("")){

        }else{
            enterpriseModels.add(new Enterprise(name,email,password));
            EnterprisesTable.setItems(enterpriseModels);
        }
    }

    @FXML
    protected void showEnterprises(){
        EnterprisesTable.setItems(enterpriseModels);
    }

    @FXML
    protected void deleteEnterprse(){
        //
    }


}
