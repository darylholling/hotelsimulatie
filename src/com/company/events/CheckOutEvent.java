package com.company.events;

import com.company.models.Hotel;

public class CheckOutEvent extends Event {
    private int id;

    public CheckOutEvent(Hotel hotel, Integer eventTime, int idGuest) {
        super(eventTime, hotel);
        this.id = idGuest;

//        if (guest.getIdGuest()== idGuest) {
////        remove idGuest van arraylist
//        }
    }


    @Override
    public void fire() {
        this.hotel.guestList.remove(hotel.getGuestById(id));
        //TODO lopen naar lobby
        //TODO add cleaning to queue
    }
}

