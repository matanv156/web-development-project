package DAO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface IDao<T> extends java.io.Serializable {

    boolean add(T object) throws IllegalArgumentException, IOException;
    boolean update(T object) throws IOException;
    boolean delete(T Object) throws IOException;
    List<T> getAll() throws IOException;
}
