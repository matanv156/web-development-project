package Controllers;

import Models.Employee;
import Services.EmployeeServices;
import java.util.List;

public class EmployeeController {
    private final EmployeeServices employeeServices;

    public EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    public void create(Employee employee) {
        System.out.println("EmployeeController:: Start Create");
        try {
            employeeServices.add(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("EmployeeController:: Finish Create");
    }

    public void update(Employee employee) {
        System.out.println("EmployeeController:: Start Update");
        try {
            employeeServices.update(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("EmployeeController:: Finish Update");
    }

    public void delete(Employee employee) {
        System.out.println("EmployeeController:: Start Delete");
        try {
            employeeServices.delete(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("EmployeeController:: Finish Delete");
    }

    public Employee get(int id) {
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
