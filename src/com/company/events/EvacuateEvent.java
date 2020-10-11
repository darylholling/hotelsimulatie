package com.company.events;

import com.company.actions.Dijkstra;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;

import java.util.LinkedList;

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
            Dijkstra dijkstra = new Dijkstra();
            guest.getArea().setDistanceForPerson(guest, 0);
            LinkedList<Area> path = dijkstra.findPath(guest, guest.getArea(), lobby);
            guest.setMovingQueue(path);
        }
    }
}
