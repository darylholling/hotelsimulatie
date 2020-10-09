package com.company.models;

import java.util.ArrayList;
import java.util.Timer;

public class Time implements StartListener{
    private static Timer timer;
    static boolean running;
    private ArrayList<HTEListener> HTElisteners;
    private Settings settings;
    private HteCounter hteCounter;

    public boolean isRunning() {
        return running;
    }

    public Time(ArrayList<HTEListener> HTElisteners, Settings settings) {
        this.HTElisteners = HTElisteners;
        this.settings = settings;
    }

    public void startTimer() {
        int hteTime = settings.getHTETime();
        timer = new Timer();
        running = true;
        this.hteCounter = new HteCounter(this.HTElisteners);
        timer.scheduleAtFixedRate(this.hteCounter, hteTime, hteTime);
    }

    public void stopTimer() {
        running = false;
        timer.cancel();
    }

    public void resumeTimer(){
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

    public HteCounter getHteCounter() {
        return hteCounter;
    }

    public void setHteCounter(HteCounter hteCounter) {
        this.hteCounter = hteCounter;
    }
}





