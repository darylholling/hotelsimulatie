package com.company.models;

import java.util.ArrayList;
import java.util.TimerTask;

public class HteCounter extends TimerTask {
    public static int hte;
    private ArrayList<HTEListener> HTElisteners;

    public HteCounter(ArrayList<HTEListener> HTElisteners) {
        this.HTElisteners = HTElisteners;
    }

    public static int getHte() {
        return hte;
    }

    public void run() {
        hte++;

        this.update();
    }

    private void update() {
        for (HTEListener HTElistener : HTElisteners) {
            HTElistener.updatedHTE(hte);
        }
    }
}