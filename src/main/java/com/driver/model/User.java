package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String phoneNumber;

    private String password;

    //Mapping with Reservation, parent - user, child - reservation
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();

    public User() {
    }

    public User(String name, String phoneNumber, String password, List<Reservation> reservationList) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.reservationList = reservationList;
    }

    public User(String name, String phoneNumber, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}
