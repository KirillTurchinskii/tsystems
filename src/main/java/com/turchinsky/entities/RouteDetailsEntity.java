package com.turchinsky.entities;


import javax.persistence.*;

@Entity
@Table(name = "route_details")
@IdClass(RouteDetailsEntityPK.class)
public class RouteDetailsEntity {

    @Id
    @Column(name = "route_id", nullable = false, insertable = false, updatable = false)
    private int routeId;


    @Id
    @Column(name = "station_id", nullable = false, insertable = false, updatable = false)
    private int stationId;

    @Id
    @Column(name = "station_order"/*, nullable = false, insertable = false, updatable = false*/)
    private int stationOrder;

    @Basic
    @Column(name = "km")
    private int km;

    @Basic
    @Column(name = "type")
    private int type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "route_id", referencedColumnName = "id"/*,insertable = false, updatable = false*/)
    private RouteEntity refRouteEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "station_id", referencedColumnName = "id"/*, insertable = false, updatable = false*/)
    private StationEntity refStationEntity;

//    @OneToMany(mappedBy = "refRouteDetailsEntity")
//    private List<ScheduleDetailsEntity> refScheduleDetailsEntities;

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

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public RouteEntity getRefRouteEntity() {
        return refRouteEntity;
    }

    public void setRefRouteEntity(RouteEntity refRouteEntity) {
        this.refRouteEntity = refRouteEntity;
    }
//
//    public StationEntity getRefStationEntity() {
//        return refStationEntity;
//    }
//
//    public void setRefStationEntity(StationEntity refStationEntity) {
//        this.refStationEntity = refStationEntity;
//    }
//
//    public List<ScheduleDetailsEntity> getRefScheduleDetailsEntities() {
//        return refScheduleDetailsEntities;
//    }
//
//    public void setRefScheduleDetailsEntities(
//            List<ScheduleDetailsEntity> refScheduleDetailsEntities) {
//        this.refScheduleDetailsEntities = refScheduleDetailsEntities;
//    }

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


