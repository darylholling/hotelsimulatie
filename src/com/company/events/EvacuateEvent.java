package com.company.events;

import com.company.models.Guest;
import com.company.models.Hotel;

import java.util.ArrayList;

public class EvacuateEvent extends Event {
    private ArrayList<Guest> guests;

    public EvacuateEvent(Integer eventTime, Hotel hotel) {
        super(eventTime, hotel);
    }

    @Override
    public void fire() {

    }
}
