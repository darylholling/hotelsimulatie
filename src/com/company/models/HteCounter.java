package com.company.models;

import com.company.actions.Event;

import java.util.ArrayList;
import java.util.TimerTask;

public class HteCounter extends TimerTask
{
    ArrayList<EventListener> eventListeners = new ArrayList<>();
    public static int hte;

    public void run()
    {
        hte++;
        //hte gelijk aan een hte tijd binnen eventlist;

//        if (hte == )
//        sendEvent(Event);

        System.out.println("HTE: " + hte);
    }

    public static int getHte() {
        return hte;
    }

    public void sendEvent(Event event) {
        for (EventListener eventListener : eventListeners) {
            eventListener.execute(event);
        }
    }
}