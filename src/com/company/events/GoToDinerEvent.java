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
    public void fire() {
        if (hotel.getDiner() == null) {
            return;
        }

        Guest guest = hotel.getGuestByNumber(guestNumber);

        if (guest != null) {
            guest.addShortestPathToMovingQueueByAreaType("diner");
        }
    }
}
