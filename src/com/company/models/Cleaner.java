package com.company.models;

import com.company.events.CleaningEmergencyEvent;
import com.company.events.CleaningEvent;
import com.company.events.Event;
import com.company.models.areas.Area;

import java.util.Queue;

public class Cleaner extends Person implements CleaningListener {
    private Event event;
    private Hotel hotel;
    private Queue<CleaningEmergencyEvent> cleaningEmergencyEvents;
    private Queue<CleaningEvent> cleaningEvents;

    public Cleaner(Hotel hotel, Area area, Queue<CleaningEmergencyEvent> cleaningEmergencyEvents, Queue<CleaningEvent> cleaningEvents) {
        super.setArea(area);
        this.cleaningEmergencyEvents = cleaningEmergencyEvents;
        this.cleaningEvents = cleaningEvents;
        this.hotel = hotel;
    }

    private void checkQueue() {
        if (!cleaningEmergencyEvents.isEmpty()) {
            CleaningEmergencyEvent cleanEvent = cleaningEmergencyEvents.poll();
            hotel.lateComingHTEListeners.add(cleanEvent);
            cleanEvent.startCleaningEmergency();
        } else if (!cleaningEvents.isEmpty()) {
            CleaningEvent cleanEvent = cleaningEvents.poll();
            hotel.lateComingHTEListeners.add(cleanEvent);
            cleanEvent.startCleaningEmergency();
        } else {
            //TODO go to lobby, no cleaning things.
        }
    }

    @Override
    public void move(Area startArea, Area endArea) {

    }

    @Override
    public void updatedHTE(int HTE) {
    }

    public void startCleaners() {
        checkQueue();
    }
}


