package Controllers;

import Models.Customer;
import Services.CustomerServices;

import java.util.List;

public class CustomerController {
    private final CustomerServices customerServices;

    public CustomerController(CustomerServices customerServices) {
        this.customerServices = customerServices;
    }

    public void create(Customer customer) {
        System.out.println("CustomerController:: Start Create");
        try {
            customerServices.add(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("CustomerController:: Finish Create");
    }

    public void update(Customer customer) {
        System.out.println("CustomerController:: Start Update");
        try {
            customerServices.update(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("CustomerController:: Finish Update");
    }

    public void delete(Customer customer) {
        System.out.println("CustomerController:: Start Delete");
        try {
            customerServices.delete(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("CustomerController:: Finish Delete");
    }

    public Customer get(int id) {
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
