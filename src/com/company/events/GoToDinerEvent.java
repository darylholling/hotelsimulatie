package com.company.events;

import com.company.models.Guest;
import com.company.models.Hotel;

import java.util.ArrayList;

public class GoToDinerEvent extends Event {
    private int idGuest;

    public GoToDinerEvent(Integer eventTime, Hotel hotel, int idGuest) {
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
