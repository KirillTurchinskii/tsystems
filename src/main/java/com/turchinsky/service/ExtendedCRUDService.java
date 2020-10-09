package com.turchinsky.service;

import java.util.List;

public interface ExtendedCRUDService<T, K> {

    T get(K k);

    List<T> getAll();

    T save(T t);

    void delete(T t);

}
