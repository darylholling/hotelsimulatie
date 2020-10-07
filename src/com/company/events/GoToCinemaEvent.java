package com.company.events;

import com.company.models.Guest;
import com.company.models.Hotel;

import java.util.ArrayList;

public class GoToCinemaEvent extends Event {
    private int idGuest;
    private Hotel hotel;

    public GoToCinemaEvent(Integer eventTime, Hotel hotel, int idGuest) {
        super(eventTime, hotel);
        this.idGuest = idGuest;
        this.hotel = hotel;
    }

    @Override
    public void fire() {
        this.hotel.guestList.remove(hotel.getGuestById(idGuest));
        //todo lopen naar cinema
    }
}
