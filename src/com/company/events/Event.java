package com.company.events;

abstract public class Event implements Comparable<Event>{
    private Integer eventTime;

    public Event(Integer eventTime) {
       this.eventTime = eventTime;
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





