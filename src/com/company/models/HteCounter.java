package com.company.models;

import com.company.listeners.HTEListener;

import java.util.ArrayList;
import java.util.TimerTask;

public class HteCounter extends TimerTask {
    public static int HTE;
    private ArrayList<HTEListener> HTElisteners;

    public HteCounter(ArrayList<HTEListener> HTElisteners) {
        this.HTElisteners = HTElisteners;
    }

    //get HTE
    public static int getHTE() {
        return HTE;
    }

    //runs the timer
    public void run() {
        HTE++;
        this.update();

    }

    //send notifications to HTElistener
    private void update() {
        for (HTEListener HTElistener : HTElisteners) {
            HTElistener.updatedHTE(HTE);
        }
    }
}