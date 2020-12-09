package com.company.events;

import com.company.models.Hotel;

public class GodzillaEvent extends Event {
    //event is not being used due to time issues and priorities.
    public GodzillaEvent(Integer eventTime, Hotel hotel) {
        super(eventTime, hotel);
    }

    @Override
    //handles the notification received from FireableListener
    public void fire() {
    }
}
