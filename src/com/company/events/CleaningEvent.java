package com.company.events;

import com.company.models.Hotel;

public class CleaningEvent extends Event {
    private int idGuest;

    public CleaningEvent(Integer eventTime, Hotel hotel, int idGuest) {
        super(eventTime, hotel);
        this.idGuest = idGuest;
    }

    public int getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(int idGuest) {
        this.idGuest = idGuest;
    }

    @Override
    public void fire() {

    }

}
