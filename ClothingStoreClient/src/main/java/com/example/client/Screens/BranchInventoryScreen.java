package com.example.client.Screens;

import com.example.client.ClothingStoreClient;
import com.example.client.Models.Product;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BranchInventoryScreen extends Application {

    private ClothingStoreClient client = new ClothingStoreClient();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Branch Inventory");

        TableView<Product> inventoryTable = new TableView<>();

        TableColumn<Product, String> productNameColumn = new TableColumn<>("Product Name");
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));

        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        inventoryTable.getColumns().addAll(productNameColumn, quantityColumn);

        Button sellButton = new Button("Sell");
        sellButton.setOnAction(e -> sellProduct());

        Button buyButton = new Button("Buy");
        buyButton.setOnAction(e -> buyProduct());

        VBox vbox = new VBox(inventoryTable, sellButton, buyButton);
        Scene scene = new Scene(vbox, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void sellProduct() {
        // Send sell product request to server
    }

    private void buyProduct() {
        // Send buy product request to server
    }

    public static void main(String[] args) {
        launch(args);
    }
}