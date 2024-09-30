package DAO;

import java.io.*;
import Models.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class EmployeeDAOImpl implements IDao<Employee> {
    private final String filePath;
    private ConcurrentMap<String, Employee> employees;

    public EmployeeDAOImpl() throws IOException {
        this.filePath = "src/main/resources/ProjectData/employees.json";
        loadDataFromFile();
    }

    private void loadDataFromFile() throws IOException {
        Gson gson = new Gson();
        employees = new ConcurrentHashMap<>();
        try (FileReader reader = new FileReader(filePath)) {
            Type employeeListType = new TypeToken<List<Employee>>(){}.getType();
            List<Employee> employeeList = gson.fromJson(reader, employeeListType);
            for (Employee employee : employeeList) { employees.put(employee.getId(), employee); }
            System.out.println("Employees successfully loaded from file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading employees from file.");
        }
    }

    public void writeIntoFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            String json = gson.toJson(employees.values());
            fileWriter.write(json);
            System.out.println("Employees successfully written to file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing employees to file.");
        }
    }

    @Override
    public boolean add(Employee employee) throws IllegalArgumentException, IOException {
        employees.put(employee.getId(), employee);
        writeIntoFile();
        return true;
    }

    @Override
    public boolean update(Employee employee) throws IOException {
        if (!employees.containsKey(employee.getId())) {
            return false;
        } else {
            employees.replace(employee.getId(), employee);
            writeIntoFile();
            return true;
        }
    }

    @Override
    public boolean delete(Employee employee) throws IOException {
        if (!employees.containsKey(employee.getId())) {
            return false;
        } else {
            employees.remove(employee.getId());
            writeIntoFile();
            return true;
        }
    }

    public Employee get(String id) throws IOException { return employees.get(id); }

    @Override
    public List<Employee> getAll() throws IOException { return new ArrayList<>(employees.values()); }
}
