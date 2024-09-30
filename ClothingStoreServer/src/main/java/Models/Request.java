package Models;

import com.google.gson.JsonObject;

public class Request {
    private String controller;
    private String action;
    private JsonObject body;

    // Getters
    public String getController() { return controller; }
    public String getAction() { return action; }
    public JsonObject getBody() { return body; }

    // Setters
    public void setController(String controller) { this.controller = controller; }
    public void setAction(String action) { this.action = action; }
    public void setBody(JsonObject body) { this.body = body; }
}