package com.company.models;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.TimerTask;

public class HteCounter extends TimerTask
{
    public static int hte;
    private static ArrayList<HTEListener> HTElisteners;
    private Hotel hotel;

    public HteCounter(ArrayList<HTEListener> HTElisteners) {
       HteCounter.HTElisteners = HTElisteners;
    }

    public void run()
    {
        hte++;

        this.update();
    }

    private void update() {
        ArrayList<HTEListener> copyOfList = HTElisteners;

        for (HTEListener HTElistener : copyOfList) {
            HTElistener.updatedHTE(hte);
        }
    }

    public static int getHte() {
        return hte;
    }

//    public void handleEvent(Event event) throws Exception {
//                for (EventListener eventListener : eventListeners) {
//                    eventListener.handleEvent(event);
//        }
//    }

    public ArrayList<HTEListener> getHTElisteners() {
        return HTElisteners;
    }

    public void addHTEListener(HTEListener hteListener) {
        System.out.println("adding");
        HTElisteners.add(hteListener);
        System.out.println("done adding");
    }
}