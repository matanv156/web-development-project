package Services;

import DAO.EmployeeDAOImpl;
import Models.Employee;
import java.io.IOException;
import java.util.List;

public class EmployeeServices {
    private final EmployeeDAOImpl employeeDAO;

    public EmployeeServices(EmployeeDAOImpl employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public boolean add(Employee employee) throws IOException {
        return employeeDAO.add(employee);
    }

    public boolean update(Employee employee) throws IOException {
        return employeeDAO.update(employee);
    }

    public boolean delete(Employee employee) throws IOException {
        return employeeDAO.delete(Integer.parseInt(employee.getId()));
    }

    public Employee getById(int id) throws IOException {
        return employeeDAO.get(id);
    }

    public List getAll() throws IOException {
        return employeeDAO.getAll();
    }
}
