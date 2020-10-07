package com.company.models.areas;

import com.company.events.Event;
import com.company.models.EventListener;

import java.io.FileNotFoundException;

public class Cinema extends Area implements EventListener {
    Event event;

    public Cinema(int x, int y, int areaWidth, int areaHeight) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);
        super.setDefaultImage(this, "cinema.jpg", areaWidth);
    }

    @Override
    public void handleEvent(Event event) {
        this.startCinema();

        }

    public void startCinema (){

    }
}