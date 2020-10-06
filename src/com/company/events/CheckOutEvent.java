package com.company.events;

import com.company.models.Guest;
import com.company.models.Hotel;

import java.util.ArrayList;

public class CheckOutEvent extends Event {
    private int idGuest;
    Guest guest;

    public CheckOutEvent(Integer eventTime, Hotel hotel, int idGuest) {
        super(eventTime, hotel);
        this.idGuest = idGuest;

//        if (guest.getIdGuest()== idGuest) {
////        remove idGuest van arraylist
//        }
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

