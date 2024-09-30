package DAO;

import java.io.*;
import Models.Branch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class BranchDAOImpl implements IDao<Branch> {
    private final String filePath;
    private ConcurrentMap<Integer, Branch> branches;

    public BranchDAOImpl(String filePath) throws IOException {
        this.filePath = filePath;
        loadDataFromFile();
    }

    private void loadDataFromFile() throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            Type branchListType = new TypeToken<List<Branch>>(){}.getType();
            List<Branch> brancheList = gson.fromJson(reader, branchListType);
            for (Branch branch : brancheList) { branches.put(branch.getId(), branch); }
            System.out.println("Branches successfully loaded from file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading branches from file.");
        }
    }

    public void writeIntoFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            String json = gson.toJson(branches.values());
            fileWriter.write(json);
            System.out.println("Branches successfully written to file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing branches to file.");
        }
    }

    @Override
    public boolean add(Branch branch) throws IllegalArgumentException, IOException {
        branches.put(branch.getId(), branch);
        writeIntoFile();
        return true;
    }

    @Override
    public boolean update(Branch branch) throws IOException {
        if (!branches.containsKey(branch.getId())) {
            return false;
        } else {
            branches.replace(branch.getId(), branch);
            writeIntoFile();
            return true;
        }
    }

    @Override
    public boolean delete(int id) throws IOException {
        if (!branches.containsKey(id)) {
            return false;
        } else {
            branches.remove(id);
            writeIntoFile();
            return true;
        }
    }

    @Override
    public Branch get(int id) throws IOException { return branches.get(id); }

    @Override
    public List<Branch> getAll() throws IOException { return new ArrayList<>(branches.values()); }
}
