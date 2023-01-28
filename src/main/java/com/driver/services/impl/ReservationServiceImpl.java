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
        User user = userRepository3.findById(userId).get();
        if(user == null){
            throw new Exception("Cannot make reservation");
        }
        ParkingLot parkingLot = parkingLotRepository3.findById(parkingLotId).get();
        if(parkingLot == null){
            throw new Exception("Cannot make reservation");
        }
        List<Spot> spotList = parkingLot.getSpotList();
        boolean twoWheeler = false;
        boolean fourWheeler = false;
        boolean Others = false;
        if(numberOfWheels<=2){
            twoWheeler = true;
        }
        if(numberOfWheels >2 && numberOfWheels <= 4){
            fourWheeler = true;
        }
        if(numberOfWheels > 4){
            Others = true;
        }

        boolean availability = false;
        int Rent = Integer.MAX_VALUE;
        Spot spot1 = new Spot();

        for(Spot spot:spotList){
            if(twoWheeler){
                if(!spot.isOccupied() && spot.getSpotType()==SpotType.TWO_WHEELER || spot.getSpotType() == SpotType.FOUR_WHEELER || spot.getSpotType() == SpotType.OTHERS){
                    if(spot.getPricePerHour() < Rent){
                        spot1 = spot;
                    }
                    availability = true;
                }
            }
            if(fourWheeler){
                if(!spot.isOccupied() && spot.getSpotType() == SpotType.FOUR_WHEELER || spot.getSpotType() == SpotType.OTHERS){
                    if(spot.getPricePerHour() < Rent){
                        spot1 = spot;
                    }
                    availability = true;
                }
            }
            if(Others){
                if(!spot.isOccupied() && spot.getSpotType() == SpotType.OTHERS){
                    if(spot.getPricePerHour() < Rent){
                        spot1 = spot;
                    }
                    availability = true;
                }
            }
        }
        if(!availability){
            throw new Exception("Cannot make reservation");
        }

        Reservation reservation = new Reservation();//creating new Reservation
        reservation.setSpot(spot1);
        reservation.setNumberOfHours(timeInHours);
        reservation.setUser(user);

        List<Reservation> reservationList = spot1.getReservationList();
        reservationList.add(reservation);
        spot1.setReservationList(reservationList);
        spotRepository3.save(spot1);//saving reservation in parent spot;

        List<Reservation> reservations = user.getReservationList();
        reservations.add(reservation);
        user.setReservationList(reservations);
        userRepository3.save(user);//saving reservation in parent user;


        return reservation;
    }
}
