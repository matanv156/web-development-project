package Handlers;

import Controllers.CustomerController;
import Models.Customer;
import Models.Enums.Actions;
import Models.Enums.CustomerType;
import Models.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.List;

public class CustomerRequestHandler {
    private static Gson gson = new Gson();
    public static Response handleRequest(CustomerController customerController, String action, JsonObject body) {
        Response response = new Response();
        switch (Actions.valueOf(action)) {
            case CREATE:
                boolean created = customerController.create(getCustomerFromRequest(body));
                if (created) {
                    response.setStatus("success");
                } else {
                    response.setStatus("error");
                    response.setMessage("Customer couldn't be created.");
                }
                break;

            case UPDATE:
                boolean updated = customerController.update(getCustomerFromRequest(body));
                if (updated) {
                    response.setStatus("success");
                } else {
                    response.setStatus("error");
                    response.setMessage("Customer couldn't be updated.");
                }
                break;

            case DELETE:
                boolean deleted = customerController.delete(getCustomerFromRequest(body));
                if (deleted) {
                    response.setStatus("success");
                } else {
                    response.setStatus("error");
                    response.setMessage("Customer couldn't be deleted.");
                }
                break;

            case GET:
                String id = body.get("id").getAsString();
                Customer customer = customerController.get(id);
                if (customer != null) {
                    response.setStatus("success");
                    response.setData(gson.toJsonTree(customer));
                } else {
                    response.setStatus("error");
                    response.setMessage("Customer not found.");
                }
                break;

            case GET_ALL:
                List customers = customerController.getAll();
                if (customers != null) {
                    response.setStatus("success");
                    response.setData(gson.toJsonTree(customers));
                } else {
                    response.setStatus("error");
                    response.setMessage("No customers found.");
                }
                break;
        }
        return response;
    }

    private static Customer getCustomerFromRequest(JsonObject body) {
        return new Customer(
            body.get("id").getAsString(),
            body.get("fullName").getAsString(),
            body.get("phoneNumber").getAsString(),
            CustomerType.valueOf(body.get("type").getAsString())
        );
    }
}
