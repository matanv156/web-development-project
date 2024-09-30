package Server;

import Controllers.*;
import Handlers.BranchRequestHandler;
import Handlers.CustomerRequestHandler;
import Handlers.EmployeeRequestHandler;
import Handlers.ProductRequestHandler;
import Models.*;
import Models.Enums.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final BranchController branchController;
    private final CustomerController customerController;
    private final EmployeeController employeeController;
    private final ProductController productController;

    public ClientHandler(Socket clientSocket, BranchController branchController, CustomerController customerController, EmployeeController employeeController, ProductController productController) {
        this.clientSocket = clientSocket;
        this.branchController = branchController;
        this.customerController = customerController;
        this.employeeController = employeeController;
        this.productController = productController;
    }

    @Override
    public void run() {
        try {
            Gson gson = new Gson();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String requestString = in.readLine();
            Request request = gson.fromJson(requestString, Request.class);
            String controller = request.getController();
            String action = request.getAction();
            JsonObject body = request.getBody();
            String responseString = handleRequest(controller, action, body);
            out.println(responseString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String handleRequest(String controller, String action, JsonObject body) {
        Gson gson = new Gson();
        Response response = new Response();
        try {
            switch (Controllers.valueOf(controller)) {
                case BRANCHES:
                    response = BranchRequestHandler.handleRequest(branchController, action, body);
                    break;
                case CUSTOMERS:
                    response = CustomerRequestHandler.handleRequest(customerController, action, body);
                    break;
                case EMPLOYEES:
                    response = EmployeeRequestHandler.handleRequest(employeeController, action, body);
                    break;
                case PRODUCTS:
                    response = ProductRequestHandler.handleRequest(productController, action, body);
                    break;
            }
        } catch (IllegalArgumentException e) {
            response.setStatus("error");
            response.setMessage("Invalid controller or action");
        } catch (Exception e) {
            response.setStatus("error");
            response.setMessage("Internal server error");
            e.printStackTrace();
        }

        return gson.toJson(response);
    }
}