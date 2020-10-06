package com.company.events;

import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;

import java.util.ArrayList;

public class CleaningEvent extends Event {
    private int idGuest;
    private Area area;

    public CleaningEvent(Integer eventTime, Hotel hotel, int idGuest) {
        super(eventTime, hotel);
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
