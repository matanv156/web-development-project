package com.example.client.Screens;

import com.example.client.ClothingStoreClient;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CustomerManagementScreen extends Application {

    private ClothingStoreClient client = new ClothingStoreClient();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Customer Management");

        GridPane gridPane = new GridPane();

        Label nameLabel = new Label("Full Name:");
        TextField nameField = new TextField();
        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();

        Button addButton = new Button("Add Customer");

        addButton.setOnAction(e -> addCustomer(nameField.getText(), phoneField.getText()));

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(phoneLabel, 0, 1);
        gridPane.add(phoneField, 1, 1);
        gridPane.add(addButton, 1, 2);

        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addCustomer(String name, String phone) {
        // Send add customer request to server
    }

    public static void main(String[] args) {
        launch(args);
    }
}