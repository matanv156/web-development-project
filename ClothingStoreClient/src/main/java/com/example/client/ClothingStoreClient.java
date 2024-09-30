package com.example.client;

import com.example.client.Models.Enums.Actions;
import com.example.client.Models.Enums.Controllers;
import com.example.client.Models.Request;
import com.example.client.Models.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClothingStoreClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private final Gson gson = new Gson();

    public void startConnection(String ip, int port) throws Exception {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public Response sendRequest(Controllers controller, Actions action, JsonObject body) {
        try {
            Request request = new Request(controller.name(), action.name(), body);
            String jsonRequest = gson.toJson(request);
            out.println(jsonRequest);
            String jsonResponse = in.readLine();
            return gson.fromJson(jsonResponse, Response.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void stopConnection() throws Exception {
        in.close();
        out.close();
        clientSocket.close();
    }
}
