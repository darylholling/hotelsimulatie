package com.company.models;

import javafx.application.Platform;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Timer;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {
    ArrayList<HTEListener> HTElisteners;
    Settings settings = new Settings();
    Time time = new Time(HTElisteners,settings);

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