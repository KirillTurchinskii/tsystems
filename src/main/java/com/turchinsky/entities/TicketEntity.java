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
    @Column(name = "line_id_from")
    private int lineIdFrom;

    @Basic
    @Column(name = "line_id_to")
    private int lineIdTo;

    @Basic
    @Column(name = "holder_id")
    private int holderId;

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
    @JoinColumn(name = "line_id_from", referencedColumnName = "line_id", insertable = false, updatable = false)
    private ScheduleDetailsEntity refScheduleDetailsEntityFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id_to", referencedColumnName = "line_id", insertable = false, updatable = false)
    private ScheduleDetailsEntity refScheduleDetailsEntityTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "holder_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TicketHolderEntity refHolderEntity;


    public TicketEntity() {
    }

    public TicketEntity(int price, int stationFrom, int stationTo, int lineIdFrom, int lineIdTo, int holderId,
                        int routeGroupId) {
        this.price = price;
        this.stationFrom = stationFrom;
        this.stationTo = stationTo;
        this.lineIdFrom = lineIdFrom;
        this.lineIdTo = lineIdTo;
        this.holderId = holderId;
        this.routeGroupId = routeGroupId;
    }

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

    public int getLineIdFrom() {
        return lineIdFrom;
    }

    public void setLineIdFrom(int lineIdFrom) {
        this.lineIdFrom = lineIdFrom;
    }

    public int getLineIdTo() {
        return lineIdTo;
    }

    public void setLineIdTo(int lineIdTo) {
        this.lineIdTo = lineIdTo;
    }

    public ScheduleDetailsEntity getRefScheduleDetailsEntityFrom() {
        return refScheduleDetailsEntityFrom;
    }

    public void setRefScheduleDetailsEntityFrom(ScheduleDetailsEntity refScheduleDetailsEntityFrom) {
        this.refScheduleDetailsEntityFrom = refScheduleDetailsEntityFrom;
    }

    public ScheduleDetailsEntity getRefScheduleDetailsEntityTo() {
        return refScheduleDetailsEntityTo;
    }

    public void setRefScheduleDetailsEntityTo(ScheduleDetailsEntity refScheduleDetailsEntityTo) {
        this.refScheduleDetailsEntityTo = refScheduleDetailsEntityTo;
    }

    public int getHolderId() {
        return holderId;
    }

    public void setHolderId(int holderId) {
        this.holderId = holderId;
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


    public int getRouteGroupId() {
        return routeGroupId;
    }

    public void setRouteGroupId(int groupId) {
        this.routeGroupId = groupId;
    }

    public TicketHolderEntity getRefHolderEntity() {
        return refHolderEntity;
    }

    public void setRefHolderEntity(TicketHolderEntity refHolderEntity) {
        this.refHolderEntity = refHolderEntity;
    }

}
