package com.company.models;

import java.util.ArrayList;
import java.util.TimerTask;

public class HteCounter extends TimerTask
{
    public static int hte;
    private ArrayList<HTEListener> HTElisteners;
    private Hotel hotel;

    public HteCounter(ArrayList<HTEListener> HTElisteners) {
        this.HTElisteners = HTElisteners;
    }

    public void run()
    {
        hte++;

        System.out.println(this.HTElisteners.size());
        for (HTEListener HTElistener : this.HTElisteners) {
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
        this.HTElisteners.add(hteListener);
    }
}