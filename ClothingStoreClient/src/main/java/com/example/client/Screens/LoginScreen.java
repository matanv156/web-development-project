package com.example.client.Screens;

import com.example.client.Client;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class LoginScreen {
    private Client client;
    private GridPane layout;

    public LoginScreen(Client client, StackPane root) {
        this.client = client;
        layout = new GridPane();
        Label userLabel = new Label("ID:");
        TextField userField = new TextField();
        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();
        Button loginButton = new Button("Login");
        layout.add(userLabel, 0, 0);
        layout.add(userField, 1, 0);
        layout.add(passLabel, 0, 1);
        layout.add(passField, 1, 1);
        layout.add(loginButton, 1, 2);
        loginButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();
            client.sendMessage("LOGIN:" + username + ":" + password);
            handleLoginResponse();
        });
    }
    private void handleLoginResponse() {
        new Thread(() -> {
            try {
                String response = client.receiveMessage();
                System.out.println("Login Response: " + response);
            } catch (IOException e) {
                System.err.println("Error receiving response: " + e.getMessage());
            }
        }).start();
    }
    public Node getNode() {
        return layout;
    }
}