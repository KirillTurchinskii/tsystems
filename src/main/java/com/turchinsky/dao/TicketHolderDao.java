package com.turchinsky.dao;

import com.turchinsky.entities.TicketHolderEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

@Repository
@Component
public class TicketHolderDao implements Dao<TicketHolderEntity> {

    private static final EntityManagerFactory emFactoryObj;

    static {
        emFactoryObj = Persistence.createEntityManagerFactory("sbb-pu");
    }

    private final EntityManager entityManager = emFactoryObj.createEntityManager();

    public boolean isUnique(String name, String surname, Date birthdate) {
        Query query = entityManager.createQuery(
                "select e from TicketHolderEntity e where e.name='" + name + "' and  e.surname='" + surname + "' and e.birthDate='" + birthdate + "'");
        return query.getResultList().size() == 0;
    }

    public TicketHolderEntity getByData(TicketHolderEntity ticketHolderEntity) {
        Query query = entityManager.createQuery(
                "select e from TicketHolderEntity e where e.name='" + ticketHolderEntity
                        .getName() + "' and  e.surname='" + ticketHolderEntity
                        .getSurname() + "' and e.birthDate='" + ticketHolderEntity.getBirthDate() + "'");
        return (TicketHolderEntity) query.getSingleResult();
    }


    @Override
    public TicketHolderEntity get(int id) {
        return entityManager.find(TicketHolderEntity.class, id);
    }

    @Override
    public List<TicketHolderEntity> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM TicketHolderEntity e");
        return query.getResultList();
    }

    @Override
    public TicketHolderEntity save(TicketHolderEntity ticketHolderEntity) {
        entityManager.getTransaction().begin();
        if (ticketHolderEntity.getId() == 0) {
            entityManager.persist(ticketHolderEntity);
        } else {
            ticketHolderEntity = entityManager.merge(ticketHolderEntity);
        }
        entityManager.getTransaction().commit();
        return ticketHolderEntity;
    }

    @Override
    public void delete(TicketHolderEntity ticketHolderEntity) {
        entityManager.getTransaction().begin();
        entityManager.remove(ticketHolderEntity);
        entityManager.getTransaction().commit();
    }

}
