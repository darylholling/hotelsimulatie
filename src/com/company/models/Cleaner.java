package com.company.models;

import com.company.events.Event;
import com.company.models.areas.Area;

public class Cleaner extends Person implements EventListener {
    private Event event;

    public Cleaner(Area area) {
        super.setArea(area);
    }

    @Override
    public void handleEvent(Event event) {
                System.out.println("No event");
    }

    @Override
    public void move(Area startArea, Area endArea) {

    }
}


