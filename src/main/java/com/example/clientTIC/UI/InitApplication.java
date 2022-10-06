package com.example.clientTIC.UI;
import com.example.clientTIC.Spring.AppService;
import com.example.clientTIC.Spring.ApplicationContextProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class InitApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(InitApplication.class.getResource("LogInScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("App gimnasios");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}