package com.example.clientTIC.ui;


import com.example.clientTIC.models.Company;
import com.example.clientTIC.spring.AppService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.clientTIC.spring.ApplicationContextProvider.getApplicationContext;

public class EnterpriseController implements Initializable {

    @FXML
    private TextField nameEnterprise;

    @FXML
    private TextField emailEnterprise;

    @FXML
    private TextField nroCuenta;

    @FXML
    private TextField passwordEnterprise;

    @FXML
    private TextField nameOfCompanyToDelete;


    @FXML
    private TableView<Company> EnterprisesTable;

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
        // agregar companyEmployees
    }

    @FXML
    protected void addEnterprise(){

        String name = nameEnterprise.getText();
        String nroAccount = nroCuenta.getText();
        AppService appService=getApplicationContext().getBean(AppService.class);
        appService.addNewCompany(name,nroAccount);


    }

    @FXML
    protected void showEnterprises(){
        AppService appService=getApplicationContext().getBean(AppService.class);
        ObservableList<Company> list= appService.getListOfCompanies();
        EnterprisesTable.setItems(list);
    }

    @FXML
    protected void deleteEnterprise() {
        String companyName=nameOfCompanyToDelete.getText();
        AppService appService=getApplicationContext().getBean(AppService.class);
        appService.deleteCompanies(companyName);
    }


}
