package com.turchinsky.entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "station")
public class StationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Basic
    @Column
    private String name;


//    @OneToMany(mappedBy = "refStationEntity")
//    private List<RouteDetailsEntity> refRouteDetailsEntities;
//
//    @OneToMany(mappedBy = "refStationEntity")
//    private List<TicketEntity> refTicketFrom;
//
//    @OneToMany(mappedBy = "refStationEntity")
//    private List<TicketEntity> refTicketTo;

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
//    public List<TicketEntity> getRefTicketFrom() {
//        return refTicketFrom;
//    }
//
//    public void setRefTicketFrom(List<TicketEntity> refTicketFrom) {
//        this.refTicketFrom = refTicketFrom;
//    }
//
//    public List<TicketEntity> getRefTicketTo() {
//        return refTicketTo;
//    }
//
//    public void setRefTicketTo(List<TicketEntity> refTicketTo) {
//        this.refTicketTo = refTicketTo;
//    }

    @Override
    public String toString() {
        return "StationEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
