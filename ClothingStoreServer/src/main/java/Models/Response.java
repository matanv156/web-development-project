package Models;

import com.google.gson.JsonElement;

public class Response {
    private String status;
    private JsonElement data;
    private String message;

    // Getters
    public String getStatus() { return status; }
    public JsonElement getData() { return data; }
    public String getMessage() { return message; }

    // Setters
    public void setStatus(String status) { this.status = status; }
    public void setData(JsonElement data) { this.data = data; }
    public void setMessage(String message) { this.message = message; }
}
