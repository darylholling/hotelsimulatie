package com.company.events;

import com.company.models.Hotel;

public class DefaultCleaningEvent extends CleaningEvent {
    public DefaultCleaningEvent(Integer eventTime, Hotel hotel, int guestNumber) {
        super(eventTime, hotel, guestNumber);
    }

    @Override
    //handles the notification received from FireableListener
    public void fire() {
    }
}
