package com.company.events;

import com.company.models.Hotel;
import com.company.models.areas.Area;
import com.company.persons.Guest;

public class EvacuateEvent extends Event {
    public EvacuateEvent(Hotel hotel, Integer eventTime) {
        super(eventTime, hotel);
    }

    @Override
    public void fire() {
        if (hotel.activeGuestList.isEmpty()) {
            return;
        }

        Area lobby = this.hotel.getLobby();

        if (lobby == null) {
            return;
        }

        for (Guest guest : hotel.activeGuestList) {
            guest.setMovingQueue(guest.determineShortestPath(lobby));
        }
    }
}
