package com.company.events;

import com.company.models.Guest;

import java.util.ArrayList;

public class EvacuateEvent extends Event {
    private ArrayList<Guest> guests;

    public EvacuateEvent(Integer eventTime) {
        super(eventTime);
    }
}
