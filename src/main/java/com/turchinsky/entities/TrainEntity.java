package com.turchinsky.entities;


import javax.persistence.*;

@Entity
@Table(name = "train")
public class TrainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String number;

    @Column
    private String type;

    @Column
    private int capacity;

//    @ManyToMany
//    @JoinTable(joinColumns = @JoinColumn(name = "train_id"),
//    inverseJoinColumns = @JoinColumn(name = "station_id"))
//    private List<Station> station = new ArrayList<>();

    public TrainEntity(String type, int capacity) {
        this.type = type;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String name) {
        this.type = name;
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
                ", number='" + number + '\'' +
                ", trainType='" + type + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
