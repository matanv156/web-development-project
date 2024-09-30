package com.example.client;

import com.example.client.Screens.LoginScreen;
import javafx.application.Application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ClothingStoreClientApp extends Application {
    private Client client;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Clothing Store Management System");
        client = new Client("localhost", 12345); // Change to server IP if needed
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        loadLoginScreen(root);
    }
    private void loadLoginScreen(StackPane root) {
        LoginScreen loginScreen = new LoginScreen(client, root);
        root.getChildren().add(loginScreen.getNode());
    }
    public static void main(String[] args) {
        launch(args);
    }
}