package com.turchinsky.entities;

import javax.persistence.*;

@Entity
@Table(name = "route")
public class RouteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column
    private String name;

//    @OneToMany(mappedBy = "refRouteEntity")
//    private List<RouteDetailsEntity> refRouteDetailsEntities;
//
//    @OneToMany(mappedBy = "refRouteEntity")
//    private List<TrainHasScheduleAndRouteEntity> refTrainHasScheduleAndRouteEntities;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<RouteDetailsEntity> getRefRouteDetailsEntities() {
//        return refRouteDetailsEntities;
//    }
//
//    public void setRefRouteDetailsEntities(List<RouteDetailsEntity> refRouteDetailsEntities) {
//        this.refRouteDetailsEntities = refRouteDetailsEntities;
//    }
//
//    public List<TrainHasScheduleAndRouteEntity> getRefTrainHasScheduleAndRouteEntities() {
//        return refTrainHasScheduleAndRouteEntities;
//    }
//
//    public void setRefTrainHasScheduleAndRouteEntities(
//            List<TrainHasScheduleAndRouteEntity> refTrainHasScheduleAndRouteEntities) {
//        this.refTrainHasScheduleAndRouteEntities = refTrainHasScheduleAndRouteEntities;
//    }

    @Override
    public String toString() {
        return "RouteEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
