package com.driver.model;

import javax.persistence.*;

@Entity
@Table
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int numberOfHours;

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

    public Reservation(int numberOfHours, User user, Spot spot, Payment payment) {
        this.numberOfHours = numberOfHours;
        this.user = user;
        this.spot = spot;
        this.payment = payment;
    }

    public Reservation(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
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
