package com.turchinsky.dao;

import java.util.List;

public interface Dao<T> {

    T get(int id);

    List<T> getAll();

    T save(T t);

//    T update(T t);

    void delete(T t);

}
