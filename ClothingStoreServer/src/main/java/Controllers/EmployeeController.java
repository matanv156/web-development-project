package Controllers;

import Models.Employee;
import Services.EmployeeServices;

import java.io.IOException;
import java.util.List;

public class EmployeeController {
    private final EmployeeServices employeeServices;

    public EmployeeController() throws IOException {
        this.employeeServices = new EmployeeServices();
    }

    public boolean create(Employee employee) {
        System.out.println("EmployeeController:: Start Create");
        boolean succeeded = false;
        try {
            succeeded = employeeServices.add(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("EmployeeController:: Finish Create");
        return succeeded;
    }

    public boolean update(Employee employee) {
        System.out.println("EmployeeController:: Start Update");
        boolean succeeded = false;
        try {
            succeeded = employeeServices.update(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("EmployeeController:: Finish Update");
        return succeeded;
    }

    public boolean delete(Employee employee) {
        System.out.println("EmployeeController:: Start Delete");
        boolean succeeded = false;
        try {
            succeeded = employeeServices.delete(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("EmployeeController:: Finish Delete");
        return succeeded;
    }

    public Employee get(String id) {
        System.out.println("EmployeeController:: Start Get");
        Employee employee = null;
        try {
            employee = employeeServices.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("EmployeeController:: Finish Get");
        return employee;
    }

    public List getAll() {
        System.out.println("EmployeeController:: Start GetAll");
        List<Employee> employees = null;
        try {
            employees = employeeServices.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("EmployeeController:: Finish GetAll");
        return employees;
    }
}
