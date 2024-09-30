package com.example.client.Screens;

import com.example.client.ClothingStoreClient;
import com.example.client.Handlers.ResponseHandler;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class EmployeeManagementScreen extends Application {

    private final ClothingStoreClient client = new ClothingStoreClient();
    private TableView<Employee> tableView;
    private Employee employee;
    private ObservableList<Employee> employeeList;

    public EmployeeManagementScreen(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Employee Management");
        getEmployeeList();
        tableView = new TableView<>();
        tableView.setItems(employeeList);
        tableView.setEditable(false);

        TableColumn<Employee, Integer> employeeNumberColumn = new TableColumn<>("Employee Number");
        employeeNumberColumn.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));
        TableColumn<Employee, String> nameColumn = new TableColumn<>("Full Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        TableColumn<Employee, String> phoneColumn = new TableColumn<>("Phone Number");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        TableColumn<Employee, Integer> branchIdColumn = new TableColumn<>("Branch ID");
        branchIdColumn.setCellValueFactory(new PropertyValueFactory<>("branchId"));
        TableColumn<Employee, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        TableColumn<Employee, String> actionsColumn = new TableColumn<>("Actions");
        actionsColumn.setCellFactory(col -> new TableCell<Employee, String>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getIndex() < 0) {
                    setGraphic(null);
                } else {
                    Employee emp = getTableView().getItems().get(getTableRow().getIndex());
                    try {
                        setGraphic(createButtonBar(emp));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            private HBox createButtonBar(Employee emp) throws Exception {
                editButton.setOnAction(e -> {
                    try {
                        editEmployee(emp);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                deleteButton.setOnAction(e -> deleteEmployee(emp));
                HBox hbox = new HBox(editButton, deleteButton);
                hbox.setSpacing(10);
                return hbox;
            }
        });

        employeeNumberColumn.setPrefWidth(120);
        nameColumn.setPrefWidth(200);
        phoneColumn.setPrefWidth(120);
        branchIdColumn.setPrefWidth(100);
        roleColumn.setPrefWidth(100);
        actionsColumn.setPrefWidth(150);
        tableView.getColumns().addAll(employeeNumberColumn, nameColumn, phoneColumn, branchIdColumn, roleColumn, actionsColumn);
        Button addButton = new Button("Add Employee");
        addButton.setOnAction(e -> {
            try {
                openCreateEmployeeScreen();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        VBox vbox = new VBox(tableView, addButton);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        Scene scene = new Scene(vbox, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openCreateEmployeeScreen() throws Exception {
        CreateEmployeeScreen createEmployeeScreen = new CreateEmployeeScreen(employeeList, employee, true);
        Stage stage = new Stage();
        createEmployeeScreen.start(stage);
    }

    private void getEmployeeList() throws Exception {
        client.startConnection("localhost", 12345);
        try {
            Response response = client.sendRequest(Controllers.EMPLOYEES, Actions.GET_ALL, null);
            List<Employee> employees = ResponseHandler.getAllEmployeesFromRequest(response.getData());
            employeeList = FXCollections.observableArrayList(employees); // Correctly set the employeeList
        } catch (Exception e) {
            System.out.printf("Couldn't Get All Employees: %s", e.getMessage());
        } finally {
            client.stopConnection();
        }
    }

    private void editEmployee(Employee employee) throws Exception {
        CreateEmployeeScreen createEmployeeScreen = new CreateEmployeeScreen(employeeList, employee, false);
        Stage stage = new Stage();
        createEmployeeScreen.start(stage);
    }

    private void deleteEmployee(Employee employee) {
        System.out.println("Deleted employee: " + employee.getFullName());
//        employeeList.remove(employee);
    }
}
