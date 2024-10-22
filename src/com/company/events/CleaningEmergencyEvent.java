package com.company.events;

import com.company.listeners.CleaningListener;
import com.company.models.Hotel;

public class CleaningEmergencyEvent extends CleaningEvent {
    public CleaningEmergencyEvent(Hotel hotel, Integer eventTime, int guestNumber) {
        super(eventTime, hotel, guestNumber);
    }

    @Override
    public void fire() {
        hotel.cleaningEmergencyEvents.add(this);

        for (CleaningListener CleaningListener : cleaningListeners) {
            CleaningListener.startCleaners();
        }
    }
}
