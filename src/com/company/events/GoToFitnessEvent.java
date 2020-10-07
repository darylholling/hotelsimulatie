package com.company.events;

import com.company.models.Guest;
import com.company.models.Hotel;

import java.util.ArrayList;

public class GoToFitnessEvent extends Event {
    private int idGuest;
    private int duration;
    private Hotel hotel;

    public GoToFitnessEvent(Integer eventTime, Hotel hotel, int idGuest, int duration) {
        super(eventTime, hotel);
        this.idGuest = idGuest;
        this.duration = duration;
        this.hotel = hotel;
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

    @Override
    public void fire() {
        this.hotel.guestList.remove(hotel.getGuestById(idGuest));
        //todo lopen naar cinema
    }
}
