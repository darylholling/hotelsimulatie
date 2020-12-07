package com.company.events;

import com.company.models.Hotel;
import com.company.persons.Guest;

public class GoToFitnessEvent extends Event {
    private int guestNumber;
    private int duration;

    public GoToFitnessEvent(Integer eventTime, Hotel hotel, int guestNumber, int duration) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.duration = duration;
    }

    @Override
    public void fire() {
        Guest guest = hotel.getGuestByNumber(guestNumber);

        if (guest == null) {
            return;
        }

        guest.getArea().removePerson(guest);

        guest.addShortestPathToMovingQueueByAreaType("fitness");
    }
}

