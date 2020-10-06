package com.company.events;

import com.company.models.Guest;

public class GodzillaEvent extends Event {
    private Guest guest;

    public GodzillaEvent(Integer eventTime) {
        super(eventTime);
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
