package com.turchinsky.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "route_details")
@IdClass(RouteDetailsEntityPK.class)
public class RouteDetailsEntity {

    @Id
    @Column(name = "route_id", nullable = false, insertable = true, updatable = false)
    private int routeId;


    @Id
    @Column(name = "station_id", nullable = false, insertable = true, updatable = false)
    private int stationId;

    @Id
    @Column(name = "station_order", nullable = false)
    private int stationOrder;

    @Basic
    @Column(name = "km")
    private int km;

    @Basic
    @Column(name = "type")
    private int type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "route_id", referencedColumnName = "id", insertable = false, updatable = false)
    private RouteEntity refRouteEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "station_id", referencedColumnName = "id", insertable = false, updatable = false)
    private StationEntity refStationEntity;


    public RouteDetailsEntity(RouteEntity routeEntity, StationEntity stationEntity) {
        this.refRouteEntity = routeEntity;
        this.refStationEntity = stationEntity;
    }

    public RouteDetailsEntityPK createPK() {
        return new RouteDetailsEntityPK(this.getRouteId(), this.getStationId(),
                                        this.getStationOrder());
    }

    public boolean isPrimaryValuesNotZero(RouteDetailsEntity routeDetailsEntity) {
        return routeDetailsEntity.getRouteId() != 0 && routeDetailsEntity.getStationId() != 0 && routeDetailsEntity
                .getStationOrder() != 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RouteDetailsEntity that = (RouteDetailsEntity) o;
        return getRouteId() == that.getRouteId() &&
                getStationId() == that.getStationId() &&
                getStationOrder() == that.getStationOrder() &&
                getKm() == that.getKm() &&
                getType() == that.getType();
    }

    @Override
    public int hashCode() {
        System.out.println(this.toString());
        return Objects.hash(getRouteId(), getStationId(), getStationOrder());
    }

    @Override
    public String toString() {
        return "RouteDetailsEntity{" +
                "routeId=" + routeId +
                ", stationId=" + stationId +
                ", stationOrder=" + stationOrder +
                ", km=" + km +
                ", type=" + type +
                '}';
    }

}


