package com.company.events;

import com.company.listeners.FireableListener;
import com.company.models.Hotel;

abstract public class Event implements Comparable<Event>, FireableListener {
    protected Integer eventTime;
    protected Hotel hotel;

    public Event(Integer eventTime, Hotel hotel) {
        this.eventTime = eventTime;
        this.hotel = hotel;
    }

    public int getEventTime() {
        return eventTime;
    }

    public void setEventTime(int eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public int compareTo(Event event) {
        return Integer.compare(this.getEventTime(), event.getEventTime());
    }
}


