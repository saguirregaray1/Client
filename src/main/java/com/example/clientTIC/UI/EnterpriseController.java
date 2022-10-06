package com.example.clientTIC.UI;


import com.example.clientTIC.Spring.AppService;
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

import static com.example.clientTIC.Spring.ApplicationContextProvider.getApplicationContext;

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

    @FXML
    private TableColumn<Company,String> emailColumn;

    @FXML
    private TableColumn<Company,Long> idColumn;

    @FXML
    private TableColumn<Company,Long> nroCuentaColumn;

    @FXML
    private TableColumn<Company, String> passwordColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Company,Long>("company_id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Company,String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Company,String>("mail"));
        nroCuentaColumn.setCellValueFactory(new PropertyValueFactory<Company,Long>("nroCuenta"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Company,String>("password"));
    }

    @FXML
    protected void addEnterprise(){

        String name = nameEnterprise.getText();
        String email = emailEnterprise.getText();
        String nroAccount = nroCuenta.getText();
        String password = passwordEnterprise.getText();

        AppService appService=getApplicationContext().getBean(AppService.class);
        appService.addNewCompany(name,email,nroAccount,password);


    }

    @FXML
    protected void showEnterprises(){
        AppService appService=getApplicationContext().getBean(AppService.class);
        ObservableList<Company> list= appService.getListOfCompanies();
        EnterprisesTable.setItems(list);
    }

    @FXML
    protected void deleteEnterprse() {
        String companyName=nameOfCompanyToDelete.getText();
        AppService appService=getApplicationContext().getBean(AppService.class);
        appService.deleteCompanies(companyName);
    }


}
