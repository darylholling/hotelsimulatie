package com.company.models.areas;

import com.company.events.StartCinemaEvent;
import com.company.models.LateComingHTEListener;
import javafx.application.Platform;

import java.io.FileNotFoundException;

public class Cinema extends Area implements LateComingHTEListener {
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

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    @Override
    public void updatedHTE(int HTE) {

    }
}