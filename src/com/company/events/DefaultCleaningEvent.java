package com.company.events;

import com.company.models.Hotel;

public class DefaultCleaningEvent extends CleaningEvent {
    public DefaultCleaningEvent(Integer eventTime, Hotel hotel, int guestNumber) {
        super(eventTime, hotel, guestNumber);
    }

    @Override
    public void fire() {
    }
}
