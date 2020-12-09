package com.company.events;

import com.company.models.Hotel;
import com.company.models.areas.Area;
import com.company.persons.Guest;

public class EvacuateEvent extends Event {
    public EvacuateEvent(Hotel hotel, Integer eventTime) {
        super(eventTime, hotel);
    }

    @Override
    //handles the notification received from FireableListener
    public void fire() {
        if (hotel.activeGuestList.isEmpty()) {
            return;
        }

        Area lobby = this.hotel.getLobby();

        if (lobby == null) {
            return;
        }

        //checks all guest on the active guestList and sends them to the lobby
        for (Guest guest : hotel.activeGuestList) {
            guest.setMovingQueue(guest.determineShortestPath(lobby));
        }
    }
}
