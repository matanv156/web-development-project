package Services;

import DAO.BranchDAOImpl;
import Models.Branch;
import java.io.IOException;
import java.util.List;

public class BranchServices{
    private final BranchDAOImpl branchDAO;

    public BranchServices(BranchDAOImpl branchDAO) {
        this.branchDAO = branchDAO;
    }

    public boolean add(Branch branch) throws IOException {
        return branchDAO.add(branch);
    }

    public boolean update(Branch branch) throws IOException {
        return branchDAO.update(branch);
    }

    public boolean delete(Branch branch) throws IOException {
        return branchDAO.delete(branch.getId());
    }

    public Branch getById(int id) throws IOException {
        return branchDAO.get(id);
    }

    public List getAll() throws IOException {
        return branchDAO.getAll();
    }
}
