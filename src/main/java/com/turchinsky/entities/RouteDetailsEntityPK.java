package com.turchinsky.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class RouteDetailsEntityPK implements Serializable {

    @Id
    @Column(name = "route_id", nullable = false, insertable = false, updatable = false)
    int routeId;

    @Id
    @Column(name = "station_id", nullable = false, insertable = false, updatable = false)
    int stationId;

    @Id
    @Column(name = "station_order", nullable = false, insertable = false, updatable = false)
    int stationOrder;


    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getStationOrder() {
        return stationOrder;
    }

    public void setStationOrder(int stationOrder) {
        this.stationOrder = stationOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RouteDetailsEntityPK that = (RouteDetailsEntityPK) o;
        return routeId == that.routeId &&
                stationId == that.stationId &&
                stationOrder == that.stationOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId, stationId, stationOrder);
    }

}
