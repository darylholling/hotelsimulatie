package com.company.events;

import com.company.models.Cleaner;
import com.company.models.CleaningListener;
import com.company.models.Hotel;

import java.util.ArrayList;

abstract public class CleaningEvent extends Event {
    protected ArrayList<CleaningListener> cleaningListeners;
    protected int guestNumber;
    private int endHTE;
    private Cleaner cleaner;

    public CleaningEvent(Integer eventTime, Hotel hotel, int guestNumber,  ArrayList<CleaningListener> cleaningListeners) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.cleaningListeners = cleaningListeners;
    }

    public int getGuestNumber() {
        return guestNumber;
    }
}
