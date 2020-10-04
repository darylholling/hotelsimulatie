package com.company.models;

import java.util.TimerTask;

public class HteCounter extends TimerTask
{
    public static int hte;

    public void run()
    {
        hte++;
        System.out.println("HTE: " + hte);
    }

    public static int getHte() {
        return hte;
    }

}