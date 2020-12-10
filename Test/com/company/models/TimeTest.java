package com.company.models;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TimeTest {
    Hotel hotel = new Hotel();
    Time time = new Time(new ArrayList<>() {
        {
            add(hotel);
        }
    });

    @Test
    public void testIfTheTimerRunningBooleanWorks() {
        Assert.assertFalse(time.running);

        this.time.startTimer();
        Assert.assertTrue(time.running);

        this.time.stopTimer();
        Assert.assertFalse(time.running);
    }

    @Test
    public void checkIfHTEUpdateIsSend() throws InterruptedException {
        this.time.startTimer();
        Thread.sleep(2000);

        Assert.assertNotEquals(hotel.getCurrentHTE(), 0);
    }

    @Test
    public void checkIfStopFunctionWorks() throws InterruptedException {
        this.time.startTimer();
        Thread.sleep(2000);
        this.time.stopTimer();
        int timeAfterStop = hotel.getCurrentHTE();
        Thread.sleep(2000);
        int timeAfterSleep = hotel.getCurrentHTE();
        Assert.assertEquals(timeAfterStop, timeAfterSleep);
    }

    @Test
    public void checkIfResumeFunctionWorks() throws InterruptedException {
        this.time.startTimer();
        Thread.sleep(2000);
        int timeBeforeStop = hotel.getCurrentHTE();
        this.time.stopTimer();
        this.time.resumeTimer();
        Thread.sleep(2000);
        int timeAfterResume = hotel.getCurrentHTE();

        Assert.assertNotEquals(timeBeforeStop, timeAfterResume);
    }
}