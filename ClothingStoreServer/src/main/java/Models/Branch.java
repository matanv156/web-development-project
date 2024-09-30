package Models;

import java.util.List;

public class Branch {
    private final int id;
    private String name;
    private List<Product> inventory;

    // Constructors
    public Branch(int id) { this.id = id; }

    public Branch(int id, String name, List<Product> inventory) {
        this.id = id;
        this.name = name;
        this.inventory = inventory;
    }

    // Getters
    public int getId() { return id; }

    public String getName() { return name; }

    public List<Product> getInventory() { return inventory; }

    // Setters
    public void setInventory(List<Product> inventory) { this.inventory = inventory; }
}