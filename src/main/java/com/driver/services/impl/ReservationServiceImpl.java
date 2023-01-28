package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {

        ParkingLot parkingLot = parkingLotRepository3.findById(parkingLotId).get();

        User user = userRepository3.findById(userId).get();

        if(parkingLot==null || user==null){
            throw new Exception("Cannot make reservation");
        }

        List<Spot> spotList = parkingLot.getSpotList();

        Integer minbill = Integer.MAX_VALUE;

        Spot reqspot=null;
        for(Spot spot:spotList){
            if(returnSpot(spot.getSpotType())>=numberOfWheels && spot.getOccupied()==false && spot.getPricePerHour()*timeInHours<minbill){
                minbill=spot.getPricePerHour()*timeInHours;
                reqspot=spot;
            }
        }
        if (reqspot==null){
            throw new Exception("Cannot make reservation");
        }

        Reservation reservation = new Reservation(timeInHours);
        reservation.setUser(user);
        List<Reservation> reservationList = user.getReservationList();
        if(reservationList==null){
            reservationList = new ArrayList<>();
        }
        reservationList.add(reservation);
        user.setReservationList(reservationList);


        reservation.setSpot(reqspot);
        List<Reservation> reservationList1 = reqspot.getReservationList();
        if(reservationList1==null){
            reservationList1 = new ArrayList<>();
        }
        reservationList1.add(reservation);
        reqspot.setReservationList(reservationList1);
        reqspot.setOccupied(true);

        userRepository3.save(user);
        spotRepository3.save(reqspot);

        return reservation;
    }

    public int returnSpot(SpotType spotType){
        if (spotType==SpotType.TWO_WHEELER){
            return 2;
        }
        else if (spotType==SpotType.FOUR_WHEELER) {
            return 4;
        }
        else return Integer.MAX_VALUE;
    }
}
