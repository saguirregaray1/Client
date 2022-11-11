package com.example.clientTIC.ui;


import com.example.clientTIC.models.Company;
import com.example.clientTIC.spring.AppService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
        idColumn.setCellValueFactory(new PropertyValueFactory<Company,Long>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Company,String>("nombre"));
        nroCuentaColumn.setCellValueFactory(new PropertyValueFactory<Company,Long>("nroCuenta"));
        showEnterprises();
    }

    @FXML
    protected void addEnterprise(){
        String name = companyName.getText();
        Long nroAccount = Long.valueOf(nroCuenta.getText());
        String email = emailEnterprise.getText(); //fixme agregar campo
        String password = passwordEnterprise.getText(); //fixme agregar campo
        AppService appService=getApplicationContext().getBean(AppService.class);
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
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


}
