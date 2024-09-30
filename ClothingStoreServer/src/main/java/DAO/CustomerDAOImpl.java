package DAO;

import Models.Customer;
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

public class CustomerDAOImpl implements IDao<Customer> {
    private final String filePath;
    private ConcurrentMap<String, Customer> customers;

    public CustomerDAOImpl(String filePath) throws IOException {
        this.filePath = filePath;
        loadDataFromFile();
    }

    private void loadDataFromFile() throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            Type customerListType = new TypeToken<List<Customer>>(){}.getType();
            List<Customer> customerList = gson.fromJson(reader, customerListType);
            for (Customer customer : customerList) { customers.put(customer.getId(), customer); }
            System.out.println("Customers successfully loaded from file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading customers from file.");
        }
    }

    public void writeIntoFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            String json = gson.toJson(customers.values());
            fileWriter.write(json);
            System.out.println("Customers successfully written to file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing customers to file.");
        }
    }

    @Override
    public boolean add(Customer customer) throws IllegalArgumentException, IOException {
        customers.put(customer.getId(), customer);
        writeIntoFile();
        return true;
    }

    @Override
    public boolean update(Customer customer) throws IOException {
        if (!customers.containsKey(customer.getId())) {
            return false;
        } else {
            customers.replace(customer.getId(), customer);
            writeIntoFile();
            return true;
        }
    }

    @Override
    public boolean delete(int id) throws IOException {
        if (!customers.containsKey(id)) {
            return false;
        } else {
            customers.remove(id);
            writeIntoFile();
            return true;
        }
    }

    @Override
    public Customer get(int id) throws IOException { return customers.get(id); }

    @Override
    public List<Customer> getAll() throws IOException { return new ArrayList<>(customers.values()); }
}