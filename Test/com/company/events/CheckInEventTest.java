package com.company.events;

import com.company.models.Hotel;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CheckInEventTest {
    Hotel hotel = new Hotel();
    CheckInEvent checkInEvent = new CheckInEvent(hotel,0,1,1);

    @Test
    public void checkIfGuestIsAddedToHotelGuestList(){
        //TODO not working yet
        checkInEvent.fire();
        System.out.println(hotel.guestList);
    }

}