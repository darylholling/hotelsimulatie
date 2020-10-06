package com.company.models;

import java.util.ArrayList;
import java.util.Timer;

public class Time implements StartListener{
    private static Timer timer;
    static boolean running;
    private ArrayList<HTEListener> HTElisteners;

    public boolean isRunning() {
        return running;
    }

    public Time(ArrayList<HTEListener> HTElisteners) {
        this.HTElisteners = HTElisteners;
    }

    public void startTimer() {
        timer = new Timer();
        running = true;
        HteCounter htecounter = new HteCounter(this.HTElisteners);
//        timer.scheduleAtFixedRate(htecounter, Settings.getSettings().getHTETime(),Settings.getSettings().getHTETime());
        timer.scheduleAtFixedRate(htecounter, 1000,1000);
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





