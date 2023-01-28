package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        ParkingLot parkingLot=parkingLotRepository3.findById(parkingLotId).get();
        List<Spot> spotList=parkingLot.getSpotList();

        int min =Integer.MIN_VALUE;
        Spot spots = null;
        for(Spot spot:spotList){
            if(timeInHours*spot.getPricePerHour()<min){
                spots=spot;
                min=timeInHours*spot.getPricePerHour();
            }
        }
        parkingLot.getSpotList().add(spots);
        return null;
    }
}
