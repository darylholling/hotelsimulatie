package com.company.events;

import com.company.models.Hotel;

public class CheckOutEvent extends Event {
    private int guestNumber;

    public CheckOutEvent(Hotel hotel, Integer eventTime, int guestNumber) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;

//        if (guest.getIdGuest()== idGuest) {
////        remove idGuest van arraylist
//        }
    }


    @Override
    public void fire() {
        this.hotel.guestList.remove(hotel.getGuestByNumber(guestNumber));
        //TODO lopen naar lobby
        //TODO add cleaning to queue
    }
}

