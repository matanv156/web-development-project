package Handlers;

import Controllers.ProductController;
import Models.Employee;
import Models.Enums.Actions;
import Models.Enums.ProductCategory;
import Models.Product;
import Models.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

public class ProductRequestHandler {
    private static Gson gson = new Gson();
    public static Response handleRequest(ProductController productController, String action, JsonObject body) {
        Response response = new Response();
        switch (Actions.valueOf(action)) {
            case CREATE:
                boolean created = productController.create(getProductFromRequest(body));
                if (created) {
                    response.setStatus("success");
                } else {
                    response.setStatus("error");
                    response.setMessage("Product couldn't be created.");
                }
                break;

            case UPDATE:
                boolean updated = productController.update(getProductFromRequest(body));
                if (updated) {
                    response.setStatus("success");
                } else {
                    response.setStatus("error");
                    response.setMessage("Product couldn't be updated.");
                }
                break;

            case DELETE:
                boolean deleted = productController.delete(getProductFromRequest(body));
                if (deleted) {
                    response.setStatus("success");
                } else {
                    response.setStatus("error");
                    response.setMessage("Product couldn't be deleted.");
                }
                break;

            case GET:
                int id = body.get("id").getAsInt();
                Product product = productController.get(id);
                if (product != null) {
                    response.setStatus("success");
                    response.setData(gson.toJsonTree(product));
                } else {
                    response.setStatus("error");
                    response.setMessage("Product not found.");
                }
                break;

            case GET_ALL:
                List products = productController.getAll();
                if (products != null) {
                    response.setStatus("success");
                    response.setData(gson.toJsonTree(products));
                } else {
                    response.setStatus("error");
                    response.setMessage("No products found.");
                }
                break;
        }
        return response;
    }

    private static Product getProductFromRequest(JsonObject body) {
        return new Product(
                body.get("id").getAsInt(),
                body.get("name").getAsString(),
                ProductCategory.valueOf(body.get("category").getAsString()),
                body.get("price").getAsDouble(),
                body.get("stock").getAsInt()

        );
    }
}
