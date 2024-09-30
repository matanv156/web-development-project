package DAO;

import Models.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class ProductDAOImpl implements IDao<Product> {
    private final String filePath;
    private ConcurrentMap<Integer, Product> products;

    public ProductDAOImpl(String filePath) throws IOException {
        this.filePath = filePath;
        loadDataFromFile();
    }

    private void loadDataFromFile() throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            Type productListType = new TypeToken<List<Product>>(){}.getType();
            List<Product> productList = gson.fromJson(reader, productListType);
            for (Product product : productList) { products.put(product.getId(), product); }
            System.out.println("Products successfully loaded from file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading products from file.");
        }
    }

    public void writeIntoFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            String json = gson.toJson(products.values());
            fileWriter.write(json);
            System.out.println("Products successfully written to file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing products to file.");
        }
    }

    @Override
    public boolean add(Product product) throws IllegalArgumentException, IOException {
        products.put(product.getId(), product);
        writeIntoFile();
        return true;
    }

    @Override
    public boolean update(Product product) throws IOException {
        if (!products.containsKey(product.getId())) {
            return false;
        } else {
            products.replace(product.getId(), product);
            writeIntoFile();
            return true;
        }
    }

    @Override
    public boolean delete(int id) throws IOException {
        if (!products.containsKey(id)) {
            return false;
        } else {
            products.remove(id);
            writeIntoFile();
            return true;
        }
    }

    @Override
    public Product get(int id) throws IOException { return products.get(id); }

    @Override
    public List<Product> getAll() throws IOException { return new ArrayList<>(products.values()); }
}
