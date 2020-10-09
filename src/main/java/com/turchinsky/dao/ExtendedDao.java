package com.turchinsky.dao;

import java.util.List;

public interface ExtendedDao<T, K> {

    T get(K k);

    List<T> getAll();

    T save(T t);

    void delete(T t);

}
