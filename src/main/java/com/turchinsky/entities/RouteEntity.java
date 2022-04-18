package com.turchinsky.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Data
@Entity
@Table(name = "route")
public class RouteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column
    private String name;

    public RouteEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

}

