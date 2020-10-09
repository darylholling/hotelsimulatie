package com.company.events;

import com.company.models.CleaningListener;
import com.company.models.Hotel;
import com.company.models.LateComingHTEListener;

import java.util.ArrayList;

public class CleaningEvent extends Event implements LateComingHTEListener {
    private ArrayList<CleaningListener> cleaningListeners;
    private int guestNumber;
    private int endHTE;

    public CleaningEvent(Integer eventTime, Hotel hotel, int guestNumber, ArrayList<CleaningListener> cleaningListeners) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.cleaningListeners = cleaningListeners;
    }

    public void startCleaningEmergency() {
        int beginHTE = hotel.currentHTE;

        //Todo move to room

        endHTE = beginHTE + hotel.settings.getCleanHTE();
     }

    @Override
    public void updatedHTE(int HTE) {
        if (hotel.getCurrentHTE() == endHTE) {
            for (com.company.models.CleaningListener CleaningListener : cleaningListeners) {
                CleaningListener.startCleaners();
            }
        }
    }

    @Override
    public void fire() {
    }
}
