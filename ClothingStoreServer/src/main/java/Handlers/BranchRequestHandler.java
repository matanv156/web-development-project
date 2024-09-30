package Handlers;

import Controllers.BranchController;
import Models.Branch;
import Models.Enums.Actions;
import Models.Enums.ProductCategory;
import Models.Product;
import Models.Response;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class BranchRequestHandler {
    private static Gson gson = new Gson();
    public static Response handleRequest(BranchController branchController, String action, JsonObject body) {
        Response response = new Response();
        switch (Actions.valueOf(action)) {
            case CREATE:
                boolean created = branchController.create(getBranchFromRequest(body));
                if (created) {
                    response.setStatus("success");
                } else {
                    response.setStatus("error");
                    response.setMessage("Branch couldn't be created.");
                }
                break;

            case UPDATE:
                boolean updated = branchController.update(getBranchFromRequest(body));
                if (updated) {
                    response.setStatus("success");
                } else {
                    response.setStatus("error");
                    response.setMessage("Branch couldn't be updated.");
                }
                break;

            case DELETE:
                boolean deleted = branchController.delete(getBranchFromRequest(body));
                if (deleted) {
                    response.setStatus("success");
                } else {
                    response.setStatus("error");
                    response.setMessage("Branch couldn't be deleted.");
                }
                break;

            case GET:
                int id = body.get("id").getAsInt();
                Branch branch = branchController.get(id);
                if (branch != null) {
                    response.setStatus("success");
                    response.setData(gson.toJsonTree(branch));
                } else {
                    response.setStatus("error");
                    response.setMessage("Branch not found.");
                }
                break;

            case GET_ALL:
                List branches = branchController.getAll();
                if (branches != null) {
                    response.setStatus("success");
                    response.setData(gson.toJsonTree(branches));
                } else {
                    response.setStatus("error");
                    response.setMessage("No branches found.");
                }
                break;
        }
        return response;
    }

    private static Branch getBranchFromRequest(JsonObject body) {
        List<Product> inventory = new ArrayList<>();
        for (JsonElement productElement : body.getAsJsonArray("inventory")) {
            JsonObject productObject = productElement.getAsJsonObject();
            inventory.add(new Product(
                    productObject.get("id").getAsInt(),
                    productObject.get("name").getAsString(),
                    ProductCategory.valueOf(productObject.get("category").getAsString()),
                    productObject.get("price").getAsDouble(),
                    productObject.get("stock").getAsInt()
            ));
        }
        return new Branch(body.get("id").getAsInt(), body.get("name").getAsString(), inventory);
    }
}
