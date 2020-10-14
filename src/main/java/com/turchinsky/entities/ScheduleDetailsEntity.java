package com.turchinsky.entities;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "schedule_details")
public class ScheduleDetailsEntity {

    @Basic
    @Column(name = "route_id")
    private int routeId;

    @Basic
    @Column(name = "station_id")
    private int stationId;

    @Basic
    @Column(name = "station_order")
    private int stationOrder;

    @Basic
    @Column(name = "train_id")
    private int trainId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_id"/*, nullable = false, insertable = false, updatable = false*/)
    private int lineId;

    @Basic
    @Column(name = "arrival_time")
    private Timestamp arrivalTime;

    @Basic
    @Column(name = "departure_time")
    private Timestamp departureTime;

    @Basic
    @Column(name = "free_seats")
    private int freeSeats;

    @Basic
    @Column(name = "ordered_seats")
    private int orderedSeats;

    @Basic
    @Column(name = "routegroupid")
    private int routeGroupId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
                         @JoinColumn(name = "route_id", referencedColumnName = "route_id", updatable = false,
                                     insertable = false),
                         @JoinColumn(name = "station_id", referencedColumnName = "station_id", updatable = false,
                                     insertable = false),
                         @JoinColumn(name = "station_order", referencedColumnName = "station_order", updatable = false,
                                     insertable = false)}
    )
    private RouteDetailsEntity refRouteDetailsEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id", referencedColumnName = "id", updatable = false, insertable = false)
    private TrainEntity refTrainEntity;


    public ScheduleDetailsEntity() {
    }

    public ScheduleDetailsEntity(int routeGroupId, int stationId, int stationOrder, int trainId,
                                 Timestamp arrivalTime, Timestamp departureTime, int freeSeats, int orderedSeats,
                                 int routeId) {
        this.routeId = routeId;
        this.stationId = stationId;
        this.stationOrder = stationOrder;
        this.trainId = trainId;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.freeSeats = freeSeats;
        this.orderedSeats = orderedSeats;
        this.routeGroupId = routeGroupId;
    }

    //    @OneToMany(mappedBy = "refScheduleDetailsEntity")
//    private List<TicketEntity> refTicketEntities;


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

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public int getOrderedSeats() {
        return orderedSeats;
    }

    public void setOrderedSeats(int orderedSeats) {
        this.orderedSeats = orderedSeats;
    }

    public RouteDetailsEntity getRefRouteDetailsEntity() {
        return refRouteDetailsEntity;
    }

    public void setRefRouteDetailsEntity(RouteDetailsEntity refRouteDetailsEntity) {
        this.refRouteDetailsEntity = refRouteDetailsEntity;
    }

    public int getRouteGroupId() {
        return routeGroupId;
    }

    public void setRouteGroupId(int groupId) {
        this.routeGroupId = groupId;
    }

    public TrainEntity getRefTrainEntity() {
        return refTrainEntity;
    }

    public void setRefTrainEntity(TrainEntity refTrainEntity) {
        this.refTrainEntity = refTrainEntity;
    }

//    public List<TicketEntity> getRefTicketEntities() {
//        return refTicketEntities;
//    }
//
//    public void setRefTicketEntities(List<TicketEntity> refTicketEntities) {
//        this.refTicketEntities = refTicketEntities;
//    }


    @Override public String toString() {
        return "ScheduleDetailsEntity{" +
                "routeId=" + routeId +
                ", stationId=" + stationId +
                ", stationOrder=" + stationOrder +
                ", trainId=" + trainId +
                ", lineId=" + lineId +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", freeSeats=" + freeSeats +
                ", orderedSeats=" + orderedSeats +
                ", routeGroupId=" + routeGroupId +
                '}';
    }

}
