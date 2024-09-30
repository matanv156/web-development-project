package Controllers;

import Models.Customer;
import Services.CustomerServices;

import java.io.IOException;
import java.util.List;

public class CustomerController {
    private final CustomerServices customerServices;

    public CustomerController() throws IOException {
        this.customerServices = new CustomerServices();
    }

    public boolean create(Customer customer) {
        System.out.println("CustomerController:: Start Create");
        boolean succeeded = false;
        try {
            succeeded = customerServices.add(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("CustomerController:: Finish Create");
        return succeeded;
    }

    public boolean update(Customer customer) {
        System.out.println("CustomerController:: Start Update");
        boolean succeeded = false;
        try {
            succeeded = customerServices.update(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("CustomerController:: Finish Update");
        return succeeded;
    }

    public boolean delete(Customer customer) {
        System.out.println("CustomerController:: Start Delete");
        boolean succeeded = false;
        try {
            succeeded = customerServices.delete(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("CustomerController:: Finish Delete");
        return succeeded;
    }

    public Customer get(String id) {
        System.out.println("CustomerController:: Start Get");
        Customer customer = null;
        try {
            customer = customerServices.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("CustomerController:: Finish Get");
        return customer;
    }

    public List getAll() {
        System.out.println("CustomerController:: Start GetAll");
        List<Customer> customeres = null;
        try {
            customeres = customerServices.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("CustomerController:: Finish GetAll");
        return customeres;
    }
}
