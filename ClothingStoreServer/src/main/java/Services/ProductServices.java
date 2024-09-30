package Services;


import DAO.ProductDAOImpl;
import Models.Product;
import java.io.IOException;
import java.util.List;

public class ProductServices {
    private final ProductDAOImpl productDAO;

    public ProductServices(ProductDAOImpl productDAO) {
        this.productDAO = productDAO;
    }

    public boolean add(Product product) throws IOException {
        return productDAO.add(product);
    }

    public boolean update(Product product) throws IOException {
        return productDAO.update(product);
    }

    public boolean delete(Product product) throws IOException {
        return productDAO.delete(product.getId());
    }

    public Product getById(int id) throws IOException {
        return productDAO.get(id);
    }

    public List getAll() throws IOException {
        return productDAO.getAll();
    }
}
