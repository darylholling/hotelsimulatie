package com.company.events;

public class GoToCinemaEvent extends Event {
    private int idGuest;

    public GoToCinemaEvent(Integer eventTime, int idGuest) {
        super(eventTime);
        this.idGuest = idGuest;
    }

    public int getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(int idGuest) {
        this.idGuest = idGuest;
    }
}
