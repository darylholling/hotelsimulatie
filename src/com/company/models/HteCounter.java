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

    public static int getHTE() {
        return HTE;
    }

    public void run() {
        HTE++;
        System.out.println(HTE);
        this.update();
    }

    private void update() {
        for (HTEListener HTElistener : HTElisteners) {
            HTElistener.updatedHTE(HTE);
        }
    }
}