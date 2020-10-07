package com.company.events;

import com.company.models.Guest;
import com.company.models.Hotel;

public class StartCinemaEvent extends Event {
    private Guest guest;

    public StartCinemaEvent(Integer eventTime, Hotel hotel) {
        super(eventTime, hotel);
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    @Override
    public void fire() {

    }
}
