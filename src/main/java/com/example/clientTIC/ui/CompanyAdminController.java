package com.example.clientTIC.ui;


import com.example.clientTIC.models.Company;
import com.example.clientTIC.spring.AppService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.clientTIC.spring.ApplicationContextProvider.getApplicationContext;

public class CompanyAdminController implements Initializable {

    @FXML
    private TextField companyName;

    // borrar
    @FXML
    private TextField emailEnterprise;

    @FXML
    private TextField nroCuenta;

    // borrar
    @FXML
    private TextField passwordEnterprise;

    @FXML
    private TextField nameOfCompanyToDelete;


    @FXML
    private TableView<Company> companyTable;

    @FXML
    private TableColumn<Company,String> nameColumn;

    //borrar
    @FXML
    private TableColumn<Company,String> emailColumn;

    @FXML
    private TableColumn<Company,Long> idColumn;

    @FXML
    private TableColumn<Company,Long> nroCuentaColumn;

    //borrar
    @FXML
    private TableColumn<Company, String> passwordColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        nroCuentaColumn.setCellValueFactory(new PropertyValueFactory<>("nroCuenta"));
        showEnterprises();
    }

    @FXML
    protected void addEnterprise(){
        String name = companyName.getText();
        Long nroAccount = Long.valueOf(nroCuenta.getText());
        String email = emailEnterprise.getText(); //fixme agregar campo
        String password = passwordEnterprise.getText(); //fixme agregar campo
        AppService appService=getApplicationContext().getBean(AppService.class);
        // label
        appService.addNewCompany(name,nroAccount,email,password);
        showEnterprises();
    }

    @FXML
    protected void showEnterprises(){
        companyTable.getItems().clear();
        AppService appService=getApplicationContext().getBean(AppService.class);
        ObservableList<Company> list= appService.getListOfCompanies();
        companyTable.setItems(list);
    }

    @FXML
    protected void deleteEnterprise() {
        String companyName=nameOfCompanyToDelete.getText();
        AppService appService=getApplicationContext().getBean(AppService.class);
        appService.deleteCompanies(companyName);
        showEnterprises();
    }

    @FXML
    protected void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
