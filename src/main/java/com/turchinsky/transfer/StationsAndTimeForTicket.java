package com.turchinsky.transfer;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class StationsAndTimeForTicket {

    private int stationIdFrom;
    private int stationIdTo;
    private Timestamp timeFrom;
    private Timestamp timeTo;

    public StationsAndTimeForTicket() {
    }

    public StationsAndTimeForTicket(int stationIdFrom, int stationIdTo, Timestamp timeFrom, Timestamp timeTo) {
        this.stationIdFrom = stationIdFrom;
        this.stationIdTo = stationIdTo;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public int getStationIdFrom() {
        return stationIdFrom;
    }

    public void setStationIdFrom(int stationIdFrom) {
        this.stationIdFrom = stationIdFrom;
    }

    public int getStationIdTo() {
        return stationIdTo;
    }

    public void setStationIdTo(int stationIdTo) {
        this.stationIdTo = stationIdTo;
    }

    public Timestamp getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Timestamp timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Timestamp getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Timestamp timeTo) {
        this.timeTo = timeTo;
    }

}
