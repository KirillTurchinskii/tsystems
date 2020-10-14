package com.turchinsky.entities;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id"/*, nullable = false, insertable = false, updatable = false*/)
    private int id;

    @Basic
    @Column(name = "price")
    private int price;

    @Basic
    @Column(name = "station_from")
    private int stationFrom;

    @Basic
    @Column(name = "station_to")
    private int stationTo;

    @Basic
    @Column(name = "line_id")
    private int lineId;

    @Basic
    @Column(name = "passenger_id")
    private int passengerId;

    @Basic
    @Column(name = "routegroupid")
    private int routeGroupId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_from", referencedColumnName = "id", insertable = false, updatable = false)
    private StationEntity refStationFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_to", referencedColumnName = "id", insertable = false, updatable = false)
    private StationEntity refStationTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id", referencedColumnName = "line_id", insertable = false, updatable = false)
    private ScheduleDetailsEntity refScheduleDetailsEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PassengerEntity refPassengerEntity;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStationFrom() {
        return stationFrom;
    }

    public void setStationFrom(int stationFrom) {
        this.stationFrom = stationFrom;
    }

    public int getStationTo() {
        return stationTo;
    }

    public void setStationTo(int stationTo) {
        this.stationTo = stationTo;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public StationEntity getRefStationFrom() {
        return refStationFrom;
    }

    public void setRefStationFrom(StationEntity refStationFrom) {
        this.refStationFrom = refStationFrom;
    }

    public StationEntity getRefStationTo() {
        return refStationTo;
    }

    public void setRefStationTo(StationEntity refStationTo) {
        this.refStationTo = refStationTo;
    }

    public ScheduleDetailsEntity getRefScheduleDetailsEntity() {
        return refScheduleDetailsEntity;
    }

    public void setRefScheduleDetailsEntity(ScheduleDetailsEntity refScheduleDetailsEntity) {
        this.refScheduleDetailsEntity = refScheduleDetailsEntity;
    }

    public int getRouteGroupId() {
        return routeGroupId;
    }

    public void setRouteGroupId(int groupId) {
        this.routeGroupId = groupId;
    }

    public PassengerEntity getRefPassengerEntity() {
        return refPassengerEntity;
    }

    public void setRefPassengerEntity(PassengerEntity refPassengerEntity) {
        this.refPassengerEntity = refPassengerEntity;
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "id=" + id +
                ", price=" + price +
                ", stationFrom=" + stationFrom +
                ", stationTo=" + stationTo +
                ", lineId=" + lineId +
                ", passengerId=" + passengerId +
                '}';
    }

}
