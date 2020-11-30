package com.company.events;

import com.company.listeners.CleaningListener;
import com.company.models.Hotel;

import java.util.ArrayList;

public class DefaultCleaningEvent extends CleaningEvent {
    public DefaultCleaningEvent(Integer eventTime, Hotel hotel, int guestNumber, ArrayList<CleaningListener> cleaningListeners) {
        super(eventTime, hotel, guestNumber, cleaningListeners);
    }

    @Override
    public void fire() {
    }
}
