package com.company;


import java.util.Timer;
import java.util.TimerTask;

public class Time {
    static Timer timer;

    static boolean running;

    public static void main(String[] args) throws InterruptedException {
        startTimer();
        Thread.sleep(5000);
        stopTimer();
        System.out.println("paused");
        Thread.sleep(6000);
        resumeTimer();
        System.out.println("resumed");
    }

    public boolean isRunning() {
        return running;
    }

    public static void startTimer() {
        timer = new Timer();
        running = true;
        HteCounter htecounter = new HteCounter();
        timer.scheduleAtFixedRate(htecounter, 1000, 1000);
    }

    public static void stopTimer() {
        running = false;
        timer.cancel();
    }

    public static void resumeTimer(){
        startTimer();
    }
}
class HteCounter extends TimerTask
{   public static int hte;
    public void run()
    {
        hte++;
        System.out.println("HTE: " + hte);
    }

}



