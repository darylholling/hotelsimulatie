package com.company;


import java.util.Timer;
import java.util.TimerTask;

public class Time {
    static Timer timer = new Timer();

    static HteCounter htecounter = new HteCounter();

    public static void main(String[] args) throws InterruptedException {
        startTimer();
        Thread.sleep(9000);
        stopTimer();
    }

    public static void startTimer() {
        timer.scheduleAtFixedRate(htecounter, 3000, 3000);
    }

    public static void stopTimer() {
        timer.cancel();
    }
}
class HteCounter extends TimerTask
{
    public static int hte = 0;
    public void run()
    {
        hte++;
        System.out.println("HTE: " + hte);
    }
}




//package com.company;
//
//
//        import java.util.Timer;
//        import java.util.TimerTask;
//
//public class Time {
//    static Timer timer = new Timer();
//
//    static HteCounter htecounter = new HteCounter();
//
//    public static void main(String[] args){
//        timer.scheduleAtFixedRate(htecounter, 3000, 3000);
//    }
//}
//
//class HteCounter extends TimerTask
//{
//    public static int hte = 0;
//    public void run()
//    {
//        hte++;
//        System.out.println("HTE: " + hte);
//    }
//}

