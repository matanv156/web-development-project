package com.example.client.Screens;

import com.example.client.ClothingStoreClient;
import com.example.client.Models.Employee;
import com.example.client.Models.Enums.Actions;
import com.example.client.Models.Enums.Controllers;
import com.example.client.Models.Response;
import com.example.client.Handlers.ResponseHandler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import com.google.gson.JsonObject;

import java.util.List;

public class LoginScreen extends Application {

    private ClothingStoreClient client = new ClothingStoreClient();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setStyle("-fx-background-color: #f0f0f0;");
        gridPane.setAlignment(Pos.CENTER);

        Label usernameLabel = new Label("ID:");
        usernameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #333;");
        TextField usernameField = new TextField();
        usernameField.setStyle("-fx-font-size: 14px; -fx-padding: 5px; -fx-background-color: #ffffff; -fx-border-color: #ccc; -fx-border-radius: 5px;");

        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #333;");
        PasswordField passwordField = new PasswordField();
        passwordField.setStyle("-fx-font-size: 14px; -fx-padding: 5px; -fx-background-color: #ffffff; -fx-border-color: #ccc; -fx-border-radius: 5px;");

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #007BFF; -fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 10px; -fx-min-width: 150px; -fx-pref-width: 150px;");
        loginButton.setPadding(new Insets(10, 20, 20, 10));

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            authenticateUser(username, password, primaryStage);
        });

        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(loginButton, 1, 2);

        GridPane.setValignment(loginButton, javafx.geometry.VPos.BOTTOM);
        GridPane.setMargin(loginButton, new Insets(20, 0, 0, 0));

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void authenticateUser(String username, String password, Stage stage) {
        try {
            client.startConnection("localhost", 12345);
            JsonObject body = new JsonObject();
            body.addProperty("id", username);
            body.addProperty("password", password);

            Response response = client.sendRequest(Controllers.EMPLOYEES, Actions.GET, body);
            Employee employee = ResponseHandler.getEmployeeFromRequest(response.getData());

            if (response.getStatus().equals("success")) {
//                if (employee.getRole().equals("MANAGER")) {
//                    new CreateEmployeeScreen().start(stage);
//                } else {
//                    new BranchInventoryScreen().start(stage);
//                }
                new HelloScreen(employee).start(stage);
            } else {
                System.out.println("Login failed");
            }

            client.stopConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}