package com.turchinsky.dao;

import com.turchinsky.entities.RouteEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Component
@Repository
public class RouteDao implements Dao<RouteEntity> {

    private static final EntityManagerFactory emFactoryObj;

    static {
        emFactoryObj = Persistence.createEntityManagerFactory("sbb-pu");
    }

    private final EntityManager entityManager = emFactoryObj.createEntityManager();


    @Override
    public RouteEntity get(int id) {
        return entityManager.find(RouteEntity.class, id);
    }

    public List<RouteEntity> getByName(String name) {
        Query query = entityManager.createQuery("SELECT e FROM RouteEntity e where e.name=" + "'" + name + "'");
        return query.getResultList();
    }

    @Override
    public List<RouteEntity> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM RouteEntity e");
        return query.getResultList();
    }

    @Override
    public RouteEntity save(RouteEntity routeEntity) {
        entityManager.getTransaction().begin();
        if (routeEntity.getId() == 0) {
            entityManager.persist(routeEntity);
        } else {
            routeEntity = entityManager.merge(routeEntity);
        }
        entityManager.getTransaction().commit();
        return routeEntity;
    }

    @Override
    public void delete(RouteEntity routeEntity) {
        entityManager.getTransaction().begin();
        entityManager.remove(routeEntity);
        entityManager.getTransaction().commit();
    }

}
