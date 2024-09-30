package Server;

import Controllers.BranchController;
import Controllers.CustomerController;
import Controllers.EmployeeController;
import Controllers.ProductController;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class ClothingStoreServer {
    private static final int PORT = 12345;

    public ClothingStoreServer() throws IOException {
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            BranchController branchController = new BranchController();
            CustomerController customerController = new CustomerController();
            EmployeeController employeeController = new EmployeeController();
            ProductController productController = new ProductController();
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());
                new Thread(new ClientHandler(clientSocket, branchController, customerController, employeeController, productController)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}