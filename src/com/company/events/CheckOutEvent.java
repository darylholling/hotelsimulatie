package com.company.events;

import com.company.models.Guest;
import com.company.models.Hotel;

public class CheckOutEvent extends Event {
    private int guestNumber;
    private Guest guest;

    public CheckOutEvent(Hotel hotel, Integer eventTime, int guestNumber) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.hotel = hotel;
//
//        if (guest.getGuestNumber()== guestNumber) {
////        remove idGuest van arraylist
//        }
    }


    @Override
    public void fire() {
        //TODO lopen naar lobby
        this.hotel.guestList.remove(hotel.getGuestByNumber(guestNumber));
        //TODO add cleaning to queue
    }
}

