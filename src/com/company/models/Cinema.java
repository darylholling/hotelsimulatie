package com.company.models;

import com.company.actions.Event;

import java.io.FileNotFoundException;

public class Cinema extends Area implements EventListener {
    Event event;

    public Cinema(int x, int y, int areaWidth, int areaHeight) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);
        super.setDefaultImage(this, "cinema.jpg", areaWidth);
    }

    @Override
    public void handleEvent() {
        this.startCinema();

        }

    public void startCinema (){

    }
}