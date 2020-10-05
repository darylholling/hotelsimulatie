package com.company.events;

import com.company.models.Guest;

public class StartCinemaEvent extends Event {
    private Guest guest;

    public StartCinemaEvent(Integer eventTime) {
        super(eventTime);
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
