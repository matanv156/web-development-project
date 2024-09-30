package com.example.client.Screens;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReportManagementScreen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Report Management");

        Label label = new Label("Generate Reports by Branch, Product, or Category:");

        Button generateButton = new Button("Generate Report");
        generateButton.setOnAction(e -> generateReport());

        VBox vbox = new VBox(label, generateButton);
        Scene scene = new Scene(vbox, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void generateReport() {
        // Send report generation request to server
    }

    public static void main(String[] args) {
        launch(args);
    }
}