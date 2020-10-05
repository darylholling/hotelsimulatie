package com.company.actions;

abstract public class Event implements Comparable<Event>{
    private String eventType;
    private Integer eventTime;

    public Event(String eventType, Integer eventTime) {
        this.eventType = eventType;
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





