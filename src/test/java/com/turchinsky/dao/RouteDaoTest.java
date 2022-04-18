//package com.turchinsky.dao;
//
//import com.turchinsky.entities.RouteEntity;
//import org.junit.*;
//
//import java.util.List;
//
//public class RouteDaoTest {
//
//    final static RouteDao routeDao = new RouteDao();
//
//    @BeforeClass
//    public static void setUp() throws Exception {
//        System.out.println("Run Route DAO Tests");
//        List<RouteEntity> all = routeDao.getAll();
//
//        for (RouteEntity routeEntity : all) {
//            routeDao.delete(routeEntity);
//        }
//
//
//    }
//
//    @AfterClass
//    public static void tearDown() throws Exception {
//        System.out.println("End Route DAO Tests");
//    }
//
//
//    @Test
//    public void save() {
//        Assert.assertEquals(0,routeDao.getAll().size());
//        RouteEntity routeEntity = new RouteEntity(666, "Route66");
//        routeDao.save(routeEntity);
//        RouteEntity routeEntity1 = new RouteEntity(777, "Route77");
//        routeDao.save(routeEntity1);
//        Assert.assertEquals(2,routeDao.getAll().size());
//    }
//
//
//    @Test
//    public void getAll() {
//        Assert.assertEquals(2,routeDao.getAll().size());
//    }
//
//    @Test
//    public void getByName() {
//        List<RouteEntity> route66 = routeDao.getByName("Route66");
//        Assert.assertEquals("Route66",route66.get(0).getName());
//        List<RouteEntity> route77 = routeDao.getByName("Route77");
//        Assert.assertEquals("Route77",route66.get(0).getName());
//    }
//
//    @Test
//    public void update() {
//
//
//    }
//
//    @Test
//    public void get() {
//    }
//
//    @Test
//    public void delete() {
//    }
//
//}