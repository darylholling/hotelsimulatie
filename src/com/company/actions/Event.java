package com.company.actions;

abstract public class Event {
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
}





