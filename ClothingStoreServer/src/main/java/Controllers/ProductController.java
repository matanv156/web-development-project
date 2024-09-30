package Controllers;

import Models.Product;
import Services.ProductServices;

import java.io.IOException;
import java.util.List;

public class ProductController {
    private final ProductServices productServices;

    public ProductController() throws IOException {
        this.productServices = new ProductServices();
    }

    public boolean create(Product product) {
        System.out.println("ProductController:: Start Create");
        boolean succeeded = false;
        try {
            succeeded = productServices.add(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ProductController:: Finish Create");
        return succeeded;
    }

    public boolean update(Product product) {
        System.out.println("ProductController:: Start Update");
        boolean succeeded = false;
        try {
            succeeded = productServices.update(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ProductController:: Finish Update");
        return succeeded;
    }

    public boolean delete(Product product) {
        System.out.println("ProductController:: Start Delete");
        boolean succeeded = false;
        try {
            succeeded = productServices.delete(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ProductController:: Finish Delete");
        return succeeded;
    }

    public Product get(int id) {
        System.out.println("ProductController:: Start Get");
        Product product = null;
        try {
            product = productServices.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ProductController:: Finish Get");
        return product;
    }

    public List getAll() {
        System.out.println("ProductController:: Start GetAll");
        List<Product> products = null;
        try {
            products = productServices.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ProductController:: Finish GetAll");
        return products;
    }
}
