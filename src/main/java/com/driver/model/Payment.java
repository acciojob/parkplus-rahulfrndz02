package com.driver.model;

import javax.persistence.*;

@Entity
@Table
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;    //auto or not....no idea

    private boolean paymentCompleted;

    private PaymentMode paymentMode;

    //Mapping with reservation
    @OneToOne
    @JoinColumn
    private Reservation reservation;


    public Payment() {
    }

    public Payment(boolean paymentCompleted, PaymentMode paymentMode, Reservation reservation) {
        this.paymentCompleted = paymentCompleted;
        this.paymentMode = paymentMode;
        this.reservation = reservation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPaymentCompleted() {
        return paymentCompleted;
    }

    public void setPaymentCompleted(boolean paymentCompleted) {
        this.paymentCompleted = paymentCompleted;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
