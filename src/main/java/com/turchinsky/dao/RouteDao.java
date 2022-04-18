package com.turchinsky.dao;

import com.turchinsky.entities.RouteEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@Repository
public class RouteDao implements Dao<RouteEntity> {

    private static final EntityManagerFactory emFactoryObj;

    static {
        emFactoryObj = Persistence.createEntityManagerFactory("sbb-pu");
    }

    private final EntityManager entityManager = emFactoryObj.createEntityManager();

    public List<RouteEntity> getByName(String name) {
        Query query = entityManager.createQuery("SELECT e FROM RouteEntity e where e.name=" + "'" + name + "'");
        return Optional.ofNullable(query.getResultList()).orElse(Collections.emptyList());
    }

    public RouteEntity update(RouteEntity routeEntity) {
        entityManager.getTransaction().begin();
        entityManager.merge(routeEntity);
        entityManager.getTransaction().commit();
        return routeEntity;
    }

    @Override
    public RouteEntity get(int id) {
        return Optional.ofNullable(entityManager.find(RouteEntity.class, id)).orElse(new RouteEntity(0, ""));
    }

    @Override
    public List<RouteEntity> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM RouteEntity e");
        return Optional.ofNullable(query.getResultList()).orElse(Collections.emptyList());
    }

    @Override
    public RouteEntity save(RouteEntity routeEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(routeEntity);
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
