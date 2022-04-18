package com.turchinsky.entities;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Date;


@Entity
@Table(name = "passenger")
public class PassengerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column
    private String name;

    @Basic
    @Column
    private String surname;

    @Basic
    @Column(name = "birth_date")
    @NotNull
    private Date birthDate;

    @Basic
    @Column(name = "username", nullable = false)
    @NotNull
    private String username;

    @Basic
    @Column(name = "email")
    @NotNull
    @Email
    private String email;

    public PassengerEntity() {
    }

    public PassengerEntity(int id, String name, String surname, Date birthDate, String username, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.username = username;
        this.email = email;
    }

    //    @SuppressWarnings("JpaAttributeTypeInspection")
//    @OneToMany(mappedBy = "refPassengerEntity")
//    List<TicketEntity> refTicketEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


//    public List<TicketEntity> getRefTicketEntities() {
//        return refTicketEntities;
//    }
//
//    public void setRefTicketEntities(List<TicketEntity> refTicketEntities) {
//        this.refTicketEntities = refTicketEntities;
//    }

    @Override
    public String toString() {
        return "PassengerEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
