package com.company.events;

import com.company.actions.Dijkstra;
import com.company.actions.HotelBuilder;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;
import com.company.models.areas.Lobby;
import javafx.application.Platform;

import java.util.LinkedList;

public class CheckOutEvent extends Event {
    private int guestNumber;

    public CheckOutEvent(Hotel hotel, Integer eventTime, int guestNumber) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;;
    }


    @Override
    public void fire() {
        //TODO lopen naar lobby


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

        //TODO deregister guest from latecominghtelisteners.
    }
}

