package com.company.events;

import com.company.actions.Dijkstra;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;

import java.util.ArrayList;
import java.util.LinkedList;

public class EvacuateEvent extends Event {
    private ArrayList<Guest> guests;

    public EvacuateEvent(Hotel hotel, Integer eventTime) {
        super(eventTime, hotel);
    }

    @Override
    public void fire() {
        if (hotel.guestList.isEmpty()) {
            return;
        }

        Area lobby = this.hotel.getLobby();

        if (lobby == null) {
            return;
        }

        System.out.println(hotel.guestList.size());
        for (Guest guest : hotel.guestList) {
            Dijkstra dijkstra = new Dijkstra();
            guest.getArea().setDistance(0);
            LinkedList<Area> path = dijkstra.findPath(guest.getArea(), lobby);
            guest.setMovingQueue(path);
        }
    }
}
