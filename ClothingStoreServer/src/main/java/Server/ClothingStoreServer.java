package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClothingStoreServer {
    private static final int PORT = 12345;
    private ServerSocket serverSocket;
    public ClothingStoreServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port: " + PORT);
        } catch (IOException e) {
            System.err.println("Could not start server: " + e.getMessage());
        }
    }
    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            } catch (IOException e) {
                System.err.println("Accept failed: " + e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        ClothingStoreServer server = new ClothingStoreServer();
        server.start();
    }
}