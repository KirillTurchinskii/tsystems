package com.turchinsky.dao;

import com.turchinsky.entities.RouteDetailsEntity;
import com.turchinsky.entities.RouteDetailsEntityPK;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class RouteDetailsDao/* implements Dao<RouteDetailsEntity>*/ {

    private static final EntityManagerFactory emFactoryObj;

    static {
        emFactoryObj = Persistence.createEntityManagerFactory("sbb-pu");
    }

    private final EntityManager entityManager = emFactoryObj.createEntityManager();


    //    @Override
    public RouteDetailsEntity get(RouteDetailsEntityPK routeDetailsEntityPK) {
        return entityManager.find(RouteDetailsEntity.class, routeDetailsEntityPK);
    }


    //    @Override
    public List<RouteDetailsEntity> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM RouteDetailsEntity e");
        return query.getResultList();
    }

    //    @Override
    public RouteDetailsEntity save(RouteDetailsEntity routeDetailsEntity) {
        return null;
    }

    //    @Override
    public void delete(RouteDetailsEntity routeDetailsEntity) {

    }

}
