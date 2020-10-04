package com.company.models;


import java.util.Timer;

public class Time implements StartListener{
    private static Timer timer;
    static boolean running;

    public static synchronized Timer getTimer() {
        return timer;
    }

    public static void main(String[] args) throws InterruptedException {
//        startTimer();
//        Thread.sleep(5000);
//        stopTimer();
//        System.out.println("paused");
//        Thread.sleep(6000);
//        resumeTimer();
//        System.out.println("resumed");
    }

    public boolean isRunning() {
        return running;
    }

    public void startTimer() {
        timer = new Timer();
        running = true;
        HteCounter htecounter = new HteCounter();
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





