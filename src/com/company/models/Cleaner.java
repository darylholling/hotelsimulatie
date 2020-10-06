package com.company.models;

import com.company.actions.Event;

public class Cleaner extends Person implements EventListener {
    Event event;

    public Cleaner() {

    }

    @Override
    public void handleEvent(Event event) {
                System.out.println("No event");

        }
    }


