package com.company.events;

import com.company.models.CleaningListener;
import com.company.models.Hotel;
import com.company.models.LateComingHTEListener;
import javafx.animation.Timeline;

import java.util.ArrayList;

public class CleaningEmergencyEvent extends Event implements LateComingHTEListener {
    private int guestNumber;
    private Hotel hotel;
    private ArrayList<CleaningListener> cleaningListeners;
    private int endHTE;

    public CleaningEmergencyEvent(Hotel hotel, Integer eventTime, int guestNumber, ArrayList<CleaningListener> cleaningListeners) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.hotel = hotel;
        this.cleaningListeners = cleaningListeners;
    }

    public void startCleaningEmergency() {
        int beginHTE = hotel.currentHTE;

        System.out.println("ik ga nu wachten: " + hotel.settings.getCleanHTE()*hotel.settings.getHTETime());
        //Todo move to room

        endHTE = beginHTE + hotel.settings.getCleanHTE();
    }

    @Override
    public void fire() {
        hotel.cleaningEmergencyEvents.add(this);
        for (CleaningListener CleaningListener : cleaningListeners) {
            CleaningListener.startCleaners();
        }
    }

    @Override
    public void updatedHTE(int HTE) {
        if (hotel.getCurrentHTE() == endHTE) {
            for (com.company.models.CleaningListener CleaningListener : cleaningListeners) {
                CleaningListener.startCleaners();
            }
        }
    }
}
