package com.company.models.areas;

import com.company.events.StartCinemaEvent;

import java.io.FileNotFoundException;

public class Cinema extends Area {
    //due to time shortage variables below are not used yet.
    private boolean isPlaying = false;
    private int endHTE;
    private StartCinemaEvent cinemaEvent;

    public Cinema(int x, int y, int areaWidth, int areaHeight) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);
        super.setDefaultImage(this, "cinema.jpg", areaWidth);
    }

    public boolean isPlaying() {
        return isPlaying = true;
    }
}