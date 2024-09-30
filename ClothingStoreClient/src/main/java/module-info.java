module com.example.clothingstoreclient {

    // Required modules
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires com.google.gson;

    opens com.example.client.Models to com.google.gson;

    exports com.example.client.Models;
    exports com.example.client to javafx.graphics;
    exports com.example.client.Screens to javafx.graphics;
}