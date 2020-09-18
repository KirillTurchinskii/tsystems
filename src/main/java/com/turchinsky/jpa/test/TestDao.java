//package com.turchinsky.jpa.test;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.Query;
//import java.util.List;
//
//public class TestDao {
//    private static final EntityManagerFactory emFactoryObj;
//    static {
//        emFactoryObj = Persistence.createEntityManagerFactory("sbb-pu");
//    }
//
//    private final EntityManager entityManager = emFactoryObj.createEntityManager();
//
//
//    public List<TestEntity> getAll() {
//        Query query = entityManager.createQuery("SELECT e FROM TestEntity e");
//
//        return query.getResultList();
//    }
//
//    public void addTest(TestEntity testEntity) {
//        entityManager.getTransaction().begin();
//        entityManager.persist(testEntity);
//        entityManager.getTransaction().commit();
//    }
//}
