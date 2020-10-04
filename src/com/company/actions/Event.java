package com.company.actions;

public class Event {
    private Integer Guest;
    private Integer stars;
    private Integer duration;
    private String eventType;
    private Integer eventTime;

    public int getEventTime() {
        return eventTime;
    }

    public void setEventTime(int eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Integer getGuest() {
        return Guest;
    }

    public void setGuest(Integer guest) {
        Guest = guest;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Event(String eventType, Integer eventTime) {
this.eventType = eventType;
this.eventTime = eventTime;

    }

}





