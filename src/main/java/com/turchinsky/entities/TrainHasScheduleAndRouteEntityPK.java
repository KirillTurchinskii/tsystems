package com.turchinsky.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

public class TrainHasScheduleAndRouteEntityPK implements Serializable {

    @Id
    @Column(name = "train_id", nullable = false, insertable = false, updatable = false)
    int trainId;

    @Id
    @Column(name = "route_id", nullable = false, insertable = false, updatable = false)
    int routeId;

    @Id
    @Column(name = "departure_time", nullable = false)
    Timestamp departureTime;

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

}
