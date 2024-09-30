package com.example.client.Screens;

import com.example.client.ClothingStoreClient;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatScreen extends Application {

    private TextArea chatArea;
    private TextField messageField;
    private ClothingStoreClient client = new ClothingStoreClient();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Branch Chat");

        chatArea = new TextArea();
        chatArea.setEditable(false);

        messageField = new TextField();
        Button sendButton = new Button("Send");

        sendButton.setOnAction(e -> sendMessage());

        VBox vbox = new VBox(chatArea, messageField, sendButton);
        Scene scene = new Scene(vbox, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void sendMessage() {
        String message = messageField.getText();
        if (!message.isEmpty()) {
            // Send chat message to the server
            chatArea.appendText("Me: " + message + "\n");
            messageField.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}