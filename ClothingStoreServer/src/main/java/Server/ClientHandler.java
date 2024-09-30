package Server;

import Controllers.BranchController;
import Controllers.CustomerController;
import Controllers.EmployeeController;
import Controllers.ProductController;
import Models.Branch;
import Models.Customer;
import Models.Employee;
import Models.Enums.Actions;
import Models.Enums.Controllers;
import Models.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Map;

public class ClientHandler implements Runnable {
    private Socket socket;
    private EmployeeController employeeController;
    private BranchController branchController;
    private ProductController productController;
    private CustomerController customerController;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public ClientHandler(Socket socket, EmployeeController employeeController, BranchController branchController, ProductController productController, CustomerController customerController, ObjectInputStream input, ObjectOutputStream output) {
        this.socket = socket;
        this.employeeController = employeeController;
        this.branchController = branchController;
        this.productController = productController;
        this.customerController = customerController;
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        try {
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                String request = (String) input.readObject();
                String response = handleRequest(request);
                output.writeObject(response);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private String handleRequest(String request) {
        String[] parts = request.split(":");
        if (parts.length != 2) {
            return "INVALID REQUEST FORMAT";
        }
        Controllers controller;
        Actions action;
        try {
            controller = Controllers.valueOf(parts[0].toUpperCase());
            action = Actions.valueOf(parts[1].toUpperCase());
        } catch (IllegalArgumentException e) {
            return "INVALID CONTROLLER OR ACTION";
        }
        switch (controller) {
            case EMPLOYEES:
                return handleEmployeeRequest(action);
            case BRANCHES:
                return handleBranchRequest(action);
            case PRODUCTS:
                return handleProductRequest(action);
            case CUSTOMERS:
                return handleCustomerRequest(action);
            default:
                return "UNKNOWN CONTROLLER";
        }
    }
    private String handleEmployeeRequest(Actions action) {
        switch (action) {
            case CREATE:
                employeeController.create(getObject(request, Employee.class));
                break;
            case UPDATE:
                employeeController.update(getObject(request, Employee.class));
                break;
            case DELETE:
                employeeController.delete(getObject(request, Employee.class));
                break;
            case GET:
                employeeController.get(getObject(request, Employee.class));
                break;
            case GET_ALL:
                employeeController.getAll();
                break;
        }
    }
    private String handleBranchRequest(Actions action) {
        // Similar implementation for branches
        return "Branch action handled.";
    }
    private String handleProductRequest(Actions action) {
        // Similar implementation for products
        return "Product action handled.";
    }
    private String handleCustomerRequest(Actions action) {
        // Similar implementation for customers
        return "Customer action handled.";
    }
}