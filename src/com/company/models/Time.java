package com.company.models;

import java.util.ArrayList;
import java.util.Timer;

public class Time implements StartListener{
    private static Timer timer;
    static boolean running;
    private ArrayList<HTEListener> HTElisteners;
    private Settings settings;

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
        HteCounter htecounter = new HteCounter(this.HTElisteners);
        timer.scheduleAtFixedRate(htecounter, hteTime, hteTime);
//        timer.scheduleAtFixedRate(htecounter, 5000,5000);
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
}





