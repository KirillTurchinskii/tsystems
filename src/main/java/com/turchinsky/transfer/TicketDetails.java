package com.turchinsky.transfer;

import org.springframework.stereotype.Component;

import java.sql.Date;


@Component
public class TicketDetails {

    private int groupId;
    private int lineIdFrom;
    private int lineIdTo;
    private String name;
    private String surname;
    private Date birthdate;

    public TicketDetails() {
    }

    public TicketDetails(int groupId, int lineIdFrom, int lineIdTo, String name, String surname, Date birthdate) {
        this.groupId = groupId;
        this.lineIdFrom = lineIdFrom;
        this.lineIdTo = lineIdTo;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

}
