package com.company.events;

import com.company.models.Hotel;
import com.company.persons.Guest;

public class GoToDinerEvent extends Event {
    private int guestNumber;

    public GoToDinerEvent(Integer eventTime, Hotel hotel, int guestNumber) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
    }

    @Override
    //handles the notification received from FireableListener
    public void fire() {
        Guest guest = hotel.getGuestByNumber(guestNumber);

        if (guest == null) {
            return;
        }

        //sends guest to diner
        guest.addShortestPathToMovingQueueByAreaType("diner");
    }
}
