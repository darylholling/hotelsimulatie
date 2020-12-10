package com.company.models;

import com.company.listeners.HTEListener;

import java.util.ArrayList;
import java.util.Timer;

public class Time {
    static boolean running;
    private static Timer timer;
    private ArrayList<HTEListener> HTEListeners;

    public Time(ArrayList<HTEListener> HTEListeners) {
        this.HTEListeners = HTEListeners;
    }

    //start timer
    public void startTimer() {
        int HTETime = Settings.getSettings().getHTETime();
        timer = new Timer();
        running = true;
        HteCounter HTECounter = new HteCounter(this.HTEListeners);
        timer.scheduleAtFixedRate(HTECounter, HTETime, HTETime);
    }

    //stop timer
    public void stopTimer() {
        running = false;
        timer.cancel();
    }


    //resume timer
    public void resumeTimer() {
        this.startTimer();
    }
}





