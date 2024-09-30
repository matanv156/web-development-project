package Handlers;

import Controllers.EmployeeController;
import Models.*;
import Models.Enums.Actions;
import Models.Enums.EmployeeRole;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.List;

public class EmployeeRequestHandler {
    private static Gson gson = new Gson();
    public static Response handleRequest(EmployeeController employeeController, String action, JsonObject body) {
        Response response = new Response();
        switch (Actions.valueOf(action)) {
            case CREATE:
                boolean created = employeeController.create(getEmployeeFromRequest(body));
                if (created) {
                    response.setStatus("success");
                } else {
                    response.setStatus("error");
                    response.setMessage("Employee couldn't be created.");
                }
                break;

            case UPDATE:
                boolean updated = employeeController.update(getEmployeeFromRequest(body));
                if (updated) {
                    response.setStatus("success");
                } else {
                    response.setStatus("error");
                    response.setMessage("Employee couldn't be updated.");
                }
                break;

            case DELETE:
                boolean deleted = employeeController.delete(getEmployeeFromRequest(body));
                if (deleted) {
                    response.setStatus("success");
                } else {
                    response.setStatus("error");
                    response.setMessage("Employee couldn't be deleted.");
                }
                break;

            case GET:
                String id = body.get("id").getAsString();
                Employee employee = employeeController.get(id);
                if (employee != null) {
                    response.setStatus("success");
                    response.setData(gson.toJsonTree(employee));
                } else {
                    response.setStatus("error");
                    response.setMessage("Employee not found.");
                }
                break;

            case GET_ALL:
                List employees = employeeController.getAll();
                if (employees != null) {
                    response.setStatus("success");
                    response.setData(gson.toJsonTree(employees));
                } else {
                    response.setStatus("error");
                    response.setMessage("No employees found.");
                }
                break;
        }
        return response;
    }

    private static Employee getEmployeeFromRequest(JsonObject body) {
        return new Employee(
                body.get("id").getAsString(),
                body.get("employeeNumber").getAsInt(),
                body.get("password").getAsString(),
                body.get("fullName").getAsString(),
                body.get("phoneNumber").getAsString(),
                body.get("branch").getAsInt(),
               body.get("role").getAsString()
        );
    }
}
