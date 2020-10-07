package com.company.events;

import com.company.models.Hotel;

public class GoToDinerEvent extends Event {
    private int guestNumber;
    private Hotel hotel;

    public GoToDinerEvent(Integer eventTime, Hotel hotel, int guestNumber) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.hotel = hotel;
    }

    public int getIdGuest() {
        return guestNumber;
    }

    public void setIdGuest(int guestNumber) {
        this.guestNumber = guestNumber;
    }

    @Override
    public void fire() {
        this.hotel.guestList.remove(hotel.getGuestByNumber(guestNumber));
        //TODO lopen naar diner
    }
}
