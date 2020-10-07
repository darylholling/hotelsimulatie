package com.company.events;

import com.company.models.Hotel;

import java.util.ArrayList;

public class GoToDinerEvent extends Event {
    private int guestId;
    private Hotel hotel;

    public GoToDinerEvent(Integer eventTime, Hotel hotel, int idGuest) {
        super(eventTime, hotel);
        this.guestId = idGuest;
        this.hotel = hotel;
    }

    public int getIdGuest() {
        return guestId;
    }

    public void setIdGuest(int idGuest) {
        this.guestId = idGuest;
    }

    @Override
    public void fire() {
        this.hotel.guestList.remove(hotel.getGuestById(guestId));
        //TODO lopen naar diner
    }
}
