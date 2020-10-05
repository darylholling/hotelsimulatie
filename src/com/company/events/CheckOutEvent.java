package com.company.events;

import com.company.actions.Event;
import com.company.models.Guest;

public class CheckOutEvent extends Event {
    private int idGuest;
    Guest guest;

    public CheckOutEvent(Integer eventTime, int idGuest) {
        super(eventTime);
        this.idGuest = idGuest;

        if (guest.getIdGuest()== idGuest) {
//        remove idGuest van arraylist
        }
    }

    public int getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(int idGuest) {
        this.idGuest = idGuest;
    }

}

