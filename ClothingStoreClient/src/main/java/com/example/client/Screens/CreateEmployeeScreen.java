package com.example.client.Screens;

import com.example.client.ClothingStoreClient;
import com.example.client.Models.Employee;
import com.example.client.Models.Enums.Actions;
import com.example.client.Models.Enums.Controllers;
import com.example.client.Models.Response;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import com.google.gson.JsonObject;
import java.util.List;

public class CreateEmployeeScreen extends Application {

    private final ClothingStoreClient client = new ClothingStoreClient();
    private List<Employee> employees;
    private Employee employee;
    private boolean creation;

    public CreateEmployeeScreen(List<Employee> employees, Employee employee, boolean creation) {
        this.employees = employees;
        this.employee = employee;
        this.creation = creation;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Admin - Employee Management");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setStyle("-fx-background-color: #f0f0f0;");
        gridPane.setAlignment(Pos.CENTER);

        Label idLabel = new Label("Employee ID:");
        idLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        TextField idField = new TextField(!creation ? employee.getId() : "");
        Label numberLabel = new Label("Employee Number:");
        numberLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        TextField numberField = new TextField(!creation ? employee.getEmployeeNumber() + "" : "");
        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        PasswordField passwordField = new PasswordField();
        Label nameLabel = new Label("Employee Full-Name:");
        nameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        TextField nameField = new TextField(!creation ? employee.getFullName() : "");
        Label phoneNumberLabel = new Label("Employee Phone-Number:");
        phoneNumberLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        TextField phoneNumberField = new TextField(!creation ? employee.getPhoneNumber() : "");
        Label branchLabel = new Label("Employee Branch:");
        branchLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        TextField branchField = new TextField(!creation ? employee.getBranchId() + "" : "");
        Label roleLabel = new Label("Employee Role:");
        roleLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        ObservableList<String> roles = FXCollections.observableArrayList(
                "Manager", "Shift_Manager", "Cashier", "Seller"
        );
        ComboBox<String> roleComboBox = new ComboBox<>(roles);
        roleComboBox.setPromptText("Select a role");

        Button createButton = new Button("submit");
        createButton.setStyle("-fx-background-color: #007BFF; -fx-text-fill: #ffffff;");
        createButton.setOnAction(e -> {
            if (validateInput(idField, numberField, passwordField, nameField, phoneNumberField, branchField, roleComboBox)) {
                String id = idField.getText();
                int number = Integer.parseInt(numberField.getText());
                String password = passwordField.getText();
                String name = nameField.getText();
                String phoneNumber = phoneNumberField.getText();
                int branch = Integer.parseInt(branchField.getText());
                String role = roleComboBox.getSelectionModel().getSelectedItem().toUpperCase();
                createEmployee(id, number, password, name, phoneNumber, branch, role);
            }
        });

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(numberLabel, 0, 1);
        gridPane.add(numberField, 1, 1);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(passwordField, 1, 2);
        gridPane.add(nameLabel, 0, 4);
        gridPane.add(nameField, 1, 4);
        gridPane.add(phoneNumberLabel, 0, 5);
        gridPane.add(phoneNumberField, 1, 5);
        gridPane.add(branchLabel, 0, 6);
        gridPane.add(branchField, 1, 6);
        gridPane.add(roleLabel, 0, 7);
        gridPane.add(roleComboBox, 1, 7);
        gridPane.add(createButton, 1, 8);

        GridPane.setValignment(createButton, javafx.geometry.VPos.BOTTOM);
        GridPane.setMargin(createButton, new Insets(20, 0, 0, 0));

        Scene scene = new Scene(gridPane, 450, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean validateInput(TextField idField, TextField numberField, PasswordField passwordField, TextField nameField, TextField phoneNumberField, TextField branchField, ComboBox<String> roleComboBox) {
        String id = idField.getText();
        String number = numberField.getText();
        String password = passwordField.getText();
        String name = nameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String branch = branchField.getText();
        String role = roleComboBox.getSelectionModel().getSelectedItem();

        if (id.isEmpty() || number.isEmpty() || password.isEmpty() || name.isEmpty() || phoneNumber.isEmpty() || branch.isEmpty() || role == null) {
            showAlert(Alert.AlertType.ERROR, "All fields are required.");
            return false;
        }

        if (password.length() < 8 || !password.matches(".*[A-Za-z].*") || !password.matches(".*[0-9].*")) {
            showAlert(Alert.AlertType.ERROR, "Password must be at least 8 characters long, contain letters and numbers.");
            return false;
        }

        try {
            Integer.parseInt(number);
            Integer.parseInt(branch);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Employee Number and Branch must be valid numbers.");
            return false;
        }

        if (creation) {
            for (Employee employee : employees) {
                if (employee.getId().equals(id)) {
                    showAlert(Alert.AlertType.ERROR, "ID Already Exists In The System.");
                    return false;
                } else if (employee.getEmployeeNumber() == Integer.parseInt(number)) {
                    showAlert(Alert.AlertType.ERROR, "Employee Number Already Exists In The System.");
                    return false;
                }
            }
        }

        return true;
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.show();
    }

    private void createEmployee(String id, int number, String password, String name, String phoneNumber, int branch, String role) {
        try {
            client.startConnection("localhost", 12345);

            JsonObject body = new JsonObject();
            body.addProperty("id", id);
            body.addProperty("employeeNumber", number);
            body.addProperty("password", password);
            body.addProperty("fullName", name);
            body.addProperty("phoneNumber", phoneNumber);
            body.addProperty("branch", branch);
            body.addProperty("role", role.toUpperCase());

            Response response = client.sendRequest(Controllers.EMPLOYEES, Actions.CREATE, body);

            if (response.getStatus().equals("success")) {
                showAlert(Alert.AlertType.INFORMATION, "Employee created successfully.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Employee creation failed: " + response.getMessage());
            }

            client.stopConnection();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error occurred during employee creation.");
        }
    }
}