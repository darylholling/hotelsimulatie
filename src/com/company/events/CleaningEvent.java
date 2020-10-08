package com.company.events;

import com.company.models.Hotel;

public class CleaningEvent extends Event {
    private int guestNumber;

    public CleaningEvent(Integer eventTime, Hotel hotel, int guestNumber) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public void setGuestNumber(int guestNumber) {
        this.guestNumber = guestNumber;
    }

    @Override
    public void fire() {

    }

}
