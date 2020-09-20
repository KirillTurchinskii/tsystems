package com.turchinsky.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trains")
public class TrainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private int capacity;

//    @ManyToMany
//    @JoinTable(joinColumns = @JoinColumn(name = "train_id"),
//    inverseJoinColumns = @JoinColumn(name = "station_id"))
//    private List<Station> station = new ArrayList<>();

    public TrainEntity(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public TrainEntity() {
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "TrainEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }

}
