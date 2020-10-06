package com.company.actions;

public class Event implements Comparable<Event>{
    private  String eventType;
    private Integer eventTime;

    public int getEventTime() {
        return eventTime;
    }

    public Event(String eventType, Integer eventTime) {
        this.eventType = eventType;
        this.eventTime = eventTime;
    }

    public void setEventTime(int eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public int compareTo(Event event) {
        return Integer.compare(this.getEventTime(), event.getEventTime());
    }
}





