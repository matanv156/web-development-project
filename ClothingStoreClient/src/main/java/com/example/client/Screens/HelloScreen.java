package com.example.client.Screens;

import com.example.client.Models.Employee;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HelloScreen extends Application {

    private Employee employee;

    public HelloScreen(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Hello Screen");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setStyle("-fx-background-color: #f0f0f0;");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        Label helloLabel = new Label("Hello, " + employee.getFullName() + "!");
        helloLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333;");
        gridPane.add(helloLabel, 0, 0, 2, 1);

        switch (employee.getRole()) {
            case "MANAGER":
                addAdminOptions(gridPane, stage);
                break;
            case "SHIFT_MANAGER":
                addShiftManagerOptions(gridPane, stage);
                break;
            case "CASHIER":
                addCashierOptions(gridPane, stage);
                break;
            case "SELLER":
                addSellerOptions(gridPane, stage);
                break;
            default:
                System.out.println("Unknown role: " + employee.getRole());
        }

        Scene scene = new Scene(gridPane, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    private void addAdminOptions(GridPane gridPane, Stage stage) {
        Button manageEmployeesBtn = new Button("Manage Employees");
        manageEmployeesBtn.setOnAction(e -> {
            try {
                new EmployeeManagementScreen(employee).start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        gridPane.add(manageEmployeesBtn, 0, 1);

        Button manageBranchesBtn = new Button("Manage Branches");
        manageBranchesBtn.setOnAction(e -> System.out.println("Navigating to Manage Branches"));
        gridPane.add(manageBranchesBtn, 0, 2);

        Button generateReportsBtn = new Button("Generate Reports");
        generateReportsBtn.setOnAction(e -> System.out.println("Navigating to Generate Reports"));
        gridPane.add(generateReportsBtn, 0, 3);
    }

    private void addShiftManagerOptions(GridPane gridPane, Stage stage) {
        Button viewSalesStatsBtn = new Button("View Sales Statistics");
        viewSalesStatsBtn.setOnAction(e -> System.out.println("Navigating to Sales Statistics"));
        gridPane.add(viewSalesStatsBtn, 0, 1);

        Button manageShiftsBtn = new Button("Manage Shifts");
        manageShiftsBtn.setOnAction(e -> System.out.println("Navigating to Shift Management"));
        gridPane.add(manageShiftsBtn, 0, 2);
    }

    private void addCashierOptions(GridPane gridPane, Stage stage) {
        Button processSalesBtn = new Button("Process Sales");
        processSalesBtn.setOnAction(e -> System.out.println("Navigating to Process Sales"));
        gridPane.add(processSalesBtn, 0, 1);

        Button handleReturnsBtn = new Button("Handle Returns");
        handleReturnsBtn.setOnAction(e -> System.out.println("Navigating to Handle Returns"));
        gridPane.add(handleReturnsBtn, 0, 2);
    }

    private void addSellerOptions(GridPane gridPane, Stage stage) {
        Button manageInventoryBtn = new Button("Manage Inventory");
        manageInventoryBtn.setOnAction(e -> System.out.println("Navigating to Manage Inventory"));
        gridPane.add(manageInventoryBtn, 0, 1);

        Button assistCustomersBtn = new Button("Assist Customers");
        assistCustomersBtn.setOnAction(e -> System.out.println("Navigating to Assist Customers"));
        gridPane.add(assistCustomersBtn, 0, 2);
    }
}