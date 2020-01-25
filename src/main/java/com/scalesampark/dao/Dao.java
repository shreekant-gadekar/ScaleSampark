package com.scalesampark.dao;

import java.util.List;

public interface Dao<T> {
    
    T get(long id) throws IndexOutOfBoundsException, Exception ;
     
    List<T> getAll();
     
    long save(T t);
     
    void update(T t);
     
    int delete(long id);
}
