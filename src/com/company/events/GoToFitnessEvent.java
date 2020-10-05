package com.company.events;

import com.company.actions.Event;

public class GoToFitnessEvent extends Event {
    private int idGuest;
    private int duration;

    public GoToFitnessEvent(Integer eventTime, int idGuest, int duration) {
        super(eventTime);
        this.idGuest = idGuest;
        this.duration = duration;
    }

    public int getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(int idGuest) {
        this.idGuest = idGuest;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}