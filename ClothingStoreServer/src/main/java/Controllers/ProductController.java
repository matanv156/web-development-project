package Controllers;

import Models.Product;
import Services.ProductServices;
import java.util.List;

public class ProductController {
    private final ProductServices productServices;

    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    public void create(Product product) {
        System.out.println("ProductController:: Start Create");
        try {
            productServices.add(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ProductController:: Finish Create");
    }

    public void update(Product product) {
        System.out.println("ProductController:: Start Update");
        try {
            productServices.update(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ProductController:: Finish Update");
    }

    public void delete(Product product) {
        System.out.println("ProductController:: Start Delete");
        try {
            productServices.delete(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ProductController:: Finish Delete");
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
