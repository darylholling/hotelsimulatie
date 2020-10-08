package com.company.events;

import com.company.models.Hotel;

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
        this.hotel.guestList.remove(hotel.getGuestByNumber(idGuest));
        //todo lopen naar cinema
    }
}
