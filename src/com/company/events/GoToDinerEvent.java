package com.company.events;

import com.company.actions.Event;

public class GoToDinerEvent extends Event {
    private int idGuest;

    public GoToDinerEvent(Integer eventTime, int idGuest) {
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
