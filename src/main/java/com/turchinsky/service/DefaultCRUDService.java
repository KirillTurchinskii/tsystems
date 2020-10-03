package com.turchinsky.service;

import java.util.List;

public interface DefaultCRUDService<T> {

    T get(int id);

    List<T> getAll();

    T save(T t);

//    T update(T t);

    void delete(T t);

}
