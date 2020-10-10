package com.company.models;

import java.util.ArrayList;
import java.util.Timer;

public class Time implements StartListener {
    static boolean running;
    private static Timer timer;
    private ArrayList<HTEListener> HTElisteners;
    private Settings settings;

    public Time(ArrayList<HTEListener> HTElisteners, Settings settings) {
        this.HTElisteners = HTElisteners;
        this.settings = settings;
    }

    public void startTimer() {
        int hteTime = settings.getHTETime();
        timer = new Timer();
        running = true;
        HteCounter hteCounter = new HteCounter(this.HTElisteners);
        timer.scheduleAtFixedRate(hteCounter, hteTime, hteTime);
    }

    public void stopTimer() {
        running = false;
        timer.cancel();
    }

    public void resumeTimer() {
        this.startTimer();
    }

    @Override
    public void handleStart() throws Exception {
        this.startTimer();
    }

    public void addHTEListener(HTEListener hteListener) {
        this.HTElisteners.add(hteListener);
        ArrayList<HTEListener> newList = this.HTElisteners;
        new HteCounter(newList);
    }
}





