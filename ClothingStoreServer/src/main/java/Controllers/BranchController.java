package Controllers;

import Models.Branch;
import Services.BranchServices;

import java.util.List;

public class BranchController {
    private final BranchServices branchServices;

    public BranchController(BranchServices branchServices) {
        this.branchServices = branchServices;
    }

    public void create(Branch branch) {
        System.out.println("BranchController:: Start Create");
        try {
            branchServices.add(branch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("BranchController:: Finish Create");
    }

    public void update(Branch branch) {
        System.out.println("BranchController:: Start Update");
        try {
            branchServices.update(branch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("BranchController:: Finish Update");
    }

    public void delete(Branch branch) {
        System.out.println("BranchController:: Start Delete");
        try {
            branchServices.delete(branch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("BranchController:: Finish Delete");
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
