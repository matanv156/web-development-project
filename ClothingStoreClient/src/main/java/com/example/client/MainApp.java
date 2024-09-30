package com.example.client;

import com.example.client.Screens.LoginScreen;
import javafx.application.Application;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
