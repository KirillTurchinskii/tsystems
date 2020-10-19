package com.turchinsky.dao;

import com.turchinsky.entities.TicketEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Repository
@Component
public class TicketsDao implements Dao<TicketEntity> {

    private static final EntityManagerFactory emFactoryObj;

    static {
        emFactoryObj = Persistence.createEntityManagerFactory("sbb-pu");
    }

    private final EntityManager entityManager = emFactoryObj.createEntityManager();

    public List<Integer> getAllHoldersByGroupId(int routeGroupId) {
        Query query =
                entityManager.createQuery("select e.holderId from TicketEntity e where e.routeGroupId=" + routeGroupId);
        return query.getResultList();
    }

    public List<TicketEntity> getByRouteGroupId(int routeGroupId) {
        Query query = entityManager.createQuery("select e from TicketEntity e where e.routeGroupId=" + routeGroupId);
        return query.getResultList();
    }


    @Override public TicketEntity get(int id) {

        return entityManager.find(TicketEntity.class, id);
    }

    @Override public List<TicketEntity> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM TicketEntity e");
        return query.getResultList();
    }

    @Override public TicketEntity save(TicketEntity ticketEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(ticketEntity);
        entityManager.getTransaction().commit();
        return ticketEntity;
    }

    @Override public void delete(TicketEntity ticketEntity) {
        entityManager.getTransaction().begin();
        entityManager.remove(ticketEntity);
        entityManager.getTransaction().commit();
    }

}
