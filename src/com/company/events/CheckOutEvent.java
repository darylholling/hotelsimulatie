package com.company.events;

import com.company.models.Hotel;

public class CheckOutEvent extends Event {
    private int id;
    private Hotel hotel;

    public CheckOutEvent(Integer eventTime, Hotel hotel, int idGuest) {
        super(eventTime, hotel);
        this.id = idGuest;
        this.hotel = hotel;

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

