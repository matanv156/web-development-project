package Server;

import Models.Enums.Actions;
import Models.Enums.Controllers;

import java.util.Map;

public class Request {
    private Controllers controller;
    private Actions action;
    private String description;
    private Object body;

    public Controllers getController() { return controller; }

    public Actions getAction() { return action; }

    public String getDescription() { return description; }

    public Object getBody() { return body; }
}
