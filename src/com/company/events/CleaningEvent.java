package com.company.events;

import com.company.models.Area;

public class CleaningEvent extends Event {
    private int idGuest;
    private Area area;

    public CleaningEvent(Integer eventTime, int idGuest) {
        super(eventTime);
    }

    public int getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(int idGuest) {
        this.idGuest = idGuest;
    }
}
