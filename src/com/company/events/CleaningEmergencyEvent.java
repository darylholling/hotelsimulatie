package com.company.events;
import com.company.models.CleaningListener;
import com.company.models.Hotel;

import java.util.ArrayList;

public class CleaningEmergencyEvent extends CleaningEvent {

    public CleaningEmergencyEvent(Hotel hotel, Integer eventTime, int guestNumber, ArrayList<CleaningListener> cleaningListeners) {
        super(eventTime, hotel, guestNumber, cleaningListeners);
    }

    @Override
    public void fire() {
        hotel.cleaningEmergencyEvents.add(this);
        for (CleaningListener CleaningListener : cleaningListeners) {
            CleaningListener.startCleaners();
        }
    }
}
