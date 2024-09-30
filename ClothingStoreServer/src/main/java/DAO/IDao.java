package DAO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface IDao<T> extends java.io.Serializable {

    boolean add(T object) throws IllegalArgumentException, IOException;
    boolean update(T object) throws IOException;
    boolean delete(int key) throws IOException;
    T get(int key) throws IOException;
    List<T> getAll() throws IOException;
}
