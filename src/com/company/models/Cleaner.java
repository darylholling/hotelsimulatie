package com.company.models;

import com.company.actions.Event;

public class Cleaner extends Person implements EventListener {
    Event event;

    public Cleaner() {

    }

    @Override
    public void handleEvent() {
        switch (event.getEventType()) {
            case "CHECK_OUT":
            case "EVACUATE":
            case "GODZILLA":

            default:
                System.out.println("No event");

        }
    }
}


