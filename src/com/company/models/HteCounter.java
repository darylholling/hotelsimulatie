package com.company.models;

import java.util.ArrayList;
import java.util.TimerTask;

public class HteCounter extends TimerTask {
    private ArrayList<EventListener> eventListeners;

    public HteCounter() {
//        parameters meegeven in de constructor?
        this.eventListeners = eventListeners;
    }

    public static int hte;

    public void run() {
        hte++;
        System.out.println("HTE: " + hte);
    }

    public static int getHte() {
        return hte;
    }

    private void handleEvent() throws Exception {
        for (EventListener eventListener : eventListeners) {
            eventListener.handleEvent();
        }
    }

}