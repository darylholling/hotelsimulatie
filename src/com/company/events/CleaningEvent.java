package com.company.events;

import com.company.listeners.CleaningListener;
import com.company.models.Hotel;

import java.util.ArrayList;

abstract public class CleaningEvent extends Event {
    public int guestNumber;
    protected ArrayList<CleaningListener> cleaningListeners;

    public CleaningEvent(Integer eventTime, Hotel hotel, int guestNumber, ArrayList<CleaningListener> cleaningListeners) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.cleaningListeners = cleaningListeners;
    }
}
