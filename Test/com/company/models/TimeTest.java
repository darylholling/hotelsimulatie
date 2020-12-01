package com.company.models;

import com.company.listeners.HTEListener;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TimeTest {
    ArrayList<HTEListener> HTElisteners;
    Settings settings = new Settings();
    Time time = new Time(HTElisteners);

    @Test
    public void testIfTheTimerStartsAndStops(){
        settings.setSettings(1,1,1,1);

        Assert.assertFalse(time.running);

        time.startTimer();
        Assert.assertTrue(time.running);

        time.stopTimer();
        Assert.assertFalse(time.running);
    }

}