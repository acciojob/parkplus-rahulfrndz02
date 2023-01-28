package com.driver.model;

import javax.persistence.*;

@Entity
@Table
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int noOfHours;

    //Mapping with user
    @ManyToOne
    @JoinColumn
    private User user;

    //Mapping with spot
    @ManyToOne
    @JoinColumn
    private Spot spot;

    //Mapping with payment, parent -reservation, child - payment
    @OneToOne
    @JoinColumn
    private Payment payment;


    public Reservation() {
    }

    public Reservation(int noOfHours, User user, Spot spot, Payment payment) {
        this.noOfHours = noOfHours;
        this.user = user;
        this.spot = spot;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoOfHours() {
        return noOfHours;
    }

    public void setNoOfHours(int noOfHours) {
        this.noOfHours = noOfHours;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
