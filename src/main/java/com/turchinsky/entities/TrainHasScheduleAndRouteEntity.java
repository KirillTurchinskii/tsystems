package com.turchinsky.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "train_has_schedule_and_route")
@IdClass(TrainHasScheduleAndRouteEntityPK.class)
public class TrainHasScheduleAndRouteEntity {

    @Id
    @Column(name = "train_id", nullable = false)
    int trainId;

    @Id
    @Column(name = "route_id", nullable = false)
    int routeId;

    @Id
    @Column(name = "departure_time", nullable = false)
    Timestamp departureTime;

    @Basic
    @Column(name = "ordered_seats")
    int orderedSeats;

    @Basic
    @Column(name = "free_seats")
    int freeSeats;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "train_id", referencedColumnName = "id", insertable = false, updatable = false)
    TrainEntity refTrainEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "route_id", referencedColumnName = "id", insertable = false, updatable = false)
    RouteEntity refRouteEntity;

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public int getOrderedSeats() {
        return orderedSeats;
    }

    public void setOrderedSeats(int orderedSeats) {
        this.orderedSeats = orderedSeats;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public TrainEntity getRefTrainEntity() {
        return refTrainEntity;
    }

    public void setRefTrainEntity(TrainEntity refTrainEntity) {
        this.refTrainEntity = refTrainEntity;
    }

    public RouteEntity getRefRouteEntity() {
        return refRouteEntity;
    }

    public void setRefRouteEntity(RouteEntity refRouteEntity) {
        this.refRouteEntity = refRouteEntity;
    }

    @Override
    public String toString() {
        return "TrainHasScheduleAndRouteEntity{" +
                "trainId=" + trainId +
                ", routeId=" + routeId +
                ", departureTime=" + departureTime +
                ", orderedSeats=" + orderedSeats +
                ", freeSeats=" + freeSeats +
                '}';
    }

}

