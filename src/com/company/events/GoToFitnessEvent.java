package com.company.events;

import com.company.models.Hotel;

public class GoToFitnessEvent extends Event {
    private int guestNumber;
    private int duration;
    private Hotel hotel;

    public GoToFitnessEvent(Integer eventTime, Hotel hotel, int guestNumber, int duration) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.duration = duration;
        this.hotel = hotel;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public void setGuestNumber(int guestNumber) {
        this.guestNumber = guestNumber;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void fire() {
        this.hotel.guestList.remove(hotel.getGuestByNumber(guestNumber));
        //todo lopen naar cinema
    }
}
