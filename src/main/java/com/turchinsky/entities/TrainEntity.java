package com.turchinsky.entities;


import javax.persistence.*;

@Entity
@Table(name = "train")
public class TrainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column
    private String number;

    @Basic
    @Column
    private String type;

    @Basic
    @Column
    private int capacity;

    @Basic
    @Column
    private int velocity;

//    @OneToMany(mappedBy = "refTrainEntity")
//    private List<ScheduleDetailsEntity> refScheduleDetailsEntities;
//
//    @OneToMany(mappedBy = "refTrainEntity")
//    private List<TrainHasScheduleAndRouteEntity> refTrainHasScheduleAndRouteEntities;
//

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


    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

//    public List<ScheduleDetailsEntity> getRefScheduleDetailsEntities() {
//        return refScheduleDetailsEntities;
//    }
//
//    public void setRefScheduleDetailsEntities(
//            List<ScheduleDetailsEntity> refScheduleDetailsEntities) {
//        this.refScheduleDetailsEntities = refScheduleDetailsEntities;
//    }
//
//    public List<TrainHasScheduleAndRouteEntity> getRefTrainHasScheduleAndRouteEntities() {
//        return refTrainHasScheduleAndRouteEntities;
//    }
//
//    public void setRefTrainHasScheduleAndRouteEntities(
//            List<TrainHasScheduleAndRouteEntity> refTrainHasScheduleAndRouteEntities) {
//        this.refTrainHasScheduleAndRouteEntities = refTrainHasScheduleAndRouteEntities;
//    }

    @Override
    public String toString() {
        return "TrainEntity{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                ", velocity=" + velocity +
                '}';
    }

}
