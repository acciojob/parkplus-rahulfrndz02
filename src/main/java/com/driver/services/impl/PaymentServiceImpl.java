package com.driver.services.impl;

import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {

        Reservation reservation=reservationRepository2.findById(reservationId).get();

        int bill= (reservation.getSpot().getPricePerHour())*reservation.getNumberOfHours();
        if(amountSent<bill){
            throw new Exception("Insufficient Amount");
        }
        if(mode !="upi"||mode!="cASh"||mode!="card"){
            throw new Exception("Payment mode not detected");
        }

        Payment payment=new Payment();
        if(mode=="CASH") payment.setPaymentMode(PaymentMode.CASH);
        else if(mode=="UPI")payment.setPaymentMode(PaymentMode.UPI);
        else if(mode=="CARD")payment.setPaymentMode(PaymentMode.CARD);

        payment.setPaymentCompleted(true);

        payment.setReservation(reservation);

        paymentRepository2.save(payment);

        return payment;
    }
}
