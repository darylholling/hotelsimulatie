package com.company.events;

import com.company.actions.Dijkstra;
import com.company.actions.HotelBuilder;
import com.company.models.CleaningListener;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;
import com.company.models.areas.Lobby;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.LinkedList;

public class CheckOutEvent extends Event {
    private ArrayList<CleaningListener> cleaningListeners;
    private int guestNumber;

    public CheckOutEvent(Hotel hotel, Integer eventTime, int guestNumber, ArrayList<CleaningListener> CleaningListener) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.cleaningListeners = CleaningListener;
    }


    @Override
    public void fire() {
//        Guest guest = this.hotel.getGuestByNumber(this.guestNumber);
//
//        if (guest == null) {
//            return;
//        }
//
//        if (guest.getArea() == null) {
//            return;
//        }
//
//        Area lobby = this.hotel.getLobby();
//
//        if (lobby == null) {
//            return;
//        }
//
//        guest.getGuestRoom().removePerson(guest);
//        guest.setGuestRoom(null);
//        guest.setMovingToCheckOut(true);
//
//        System.out.println("Q-size" + guest.getMovingQueue().size());
//        if (!guest.getMovingQueue().isEmpty()) {
//            guest.getMovingQueue().clear();
//        }
//        System.out.println("Q-size" + guest.getMovingQueue().size());
//
//        System.out.println("area" + guest.getArea().printCoordinates());
//        System.out.println("lobby" + lobby.printCoordinates());
//
//        Dijkstra dijkstra = new Dijkstra();
//        guest.getArea().setDistance(0);
//        LinkedList<Area> path = dijkstra.findPath(guest.getArea(), lobby);
//        System.out.println(path);
//        guest.setMovingQueue(path);

//        CleaningEvent cleaningEvent = new CleaningEvent(hotel.settings.getCleanHTE(), hotel, guestNumber, cleaningListeners);
//        hotel.cleaningEvents.add(cleaningEvent);
//        for (CleaningListener CleaningListener : cleaningListeners) {
//            CleaningListener.startCleaners();
//        }

        //TODO deregister guest from latecominghtelisteners.
    }
}

