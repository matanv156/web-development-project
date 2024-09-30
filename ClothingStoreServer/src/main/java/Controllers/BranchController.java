package Controllers;

import Models.Branch;
import Services.BranchServices;

import java.io.IOException;
import java.util.List;

public class BranchController {
    private final BranchServices branchServices;

    public BranchController() throws IOException {
        this.branchServices = new BranchServices();
    }

    public boolean create(Branch branch) {
        System.out.println("BranchController:: Start Create");
        boolean succeeded = false;
        try {
            succeeded = branchServices.add(branch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("BranchController:: Finish Create");
        return succeeded;
    }

    public boolean update(Branch branch) {
        System.out.println("BranchController:: Start Update");
        boolean succeeded = false;
        try {
            succeeded = branchServices.update(branch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("BranchController:: Finish Update");
        return succeeded;
    }

    public boolean delete(Branch branch) {
        System.out.println("BranchController:: Start Delete");
        boolean succeeded = false;
        try {
            succeeded = branchServices.delete(branch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("BranchController:: Finish Delete");
        return succeeded;
    }

    public Branch get(int id) {
        System.out.println("BranchController:: Start Get");
        Branch branch = null;
        try {
            branch = branchServices.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("BranchController:: Finish Get");
        return branch;
    }

    public List getAll() {
        System.out.println("BranchController:: Start GetAll");
        List<Branch> branches = null;
        try {
            branches = branchServices.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("BranchController:: Finish GetAll");
        return branches;
    }
}
