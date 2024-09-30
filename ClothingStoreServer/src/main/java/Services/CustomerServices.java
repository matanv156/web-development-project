package Services;

import DAO.CustomerDAOImpl;
import Models.Customer;
import java.io.IOException;
import java.util.List;

public class CustomerServices {
    private final CustomerDAOImpl customerDAO;

    public CustomerServices() throws IOException {
        this.customerDAO = new CustomerDAOImpl();
    }

    public boolean add(Customer customer) throws IOException {
        return customerDAO.add(customer);
    }

    public boolean update(Customer customer) throws IOException {
        return customerDAO.update(customer);
    }

    public boolean delete(Customer customer) throws IOException {
        return customerDAO.delete(customer);
    }

    public Customer getById(String id) throws IOException {
        return customerDAO.get(id);
    }

    public List getAll() throws IOException {
        return customerDAO.getAll();
    }
}
