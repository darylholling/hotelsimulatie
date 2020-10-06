package com.company.models;

import java.util.ArrayList;
import java.util.TimerTask;

public class HteCounter extends TimerTask
{
    private ArrayList<HTEListener> HTElisteners;

    public HteCounter(ArrayList<HTEListener> HTElisteners) {
        this.HTElisteners = HTElisteners;
    }

//    public class HteCounter extends TimerTask {
//        private ArrayList<EventListener> eventListeners;
//
//        public HteCounter() {
//            eventListeners = new ArrayList<EventListener>();
//        }

    public static int hte;
    public void run()
    {
        hte++;

        for (HTEListener HTElistener : HTElisteners) {
            HTElistener.updatedHTE(hte);
        }

        System.out.println("HTE: " + hte);
    }

    public static int getHte() {
        return hte;
    }

//    public void handleEvent(Event event) throws Exception {
//                for (EventListener eventListener : eventListeners) {
//                    eventListener.handleEvent(event);
//        }
//    }

}