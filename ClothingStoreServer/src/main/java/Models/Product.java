package Models;

import Models.Enums.ProductCategory;

public class Product {
    private final int id;
    private String name;
    private final ProductCategory category;
    private double price;
    private int stock;

    // Constructors
    public Product(int id, ProductCategory category) {
        this.id = id;
        this.category = category;
    }

    public Product(int id, String name, ProductCategory category, double price, int stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    // Getters
    public int getId() { return id; }

    public String getName() { return name; }

    public ProductCategory getCategory() { return category; }

    public double getPrice() { return price; }

    public int getStock() { return stock; }

    // Setters
    public void setPrice(double price) { this.price = price; }

    public void setStock(int stock) { this.stock = stock; }
}
