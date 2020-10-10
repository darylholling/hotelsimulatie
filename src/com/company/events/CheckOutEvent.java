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
        Guest guest = this.hotel.getGuestByNumber(this.guestNumber);

        if (guest == null) {
            return;
        }

        if (guest.getArea() == null) {
            return;
        }

        Area lobby = this.hotel.getLobby();

        if (lobby == null) {
            return;
        }

        guest.getGuestRoom().removePerson(guest);
        guest.setGuestRoom(null);
        guest.setMovingToCheckOut(true);

        System.out.println(guest.getMovingQueue());
        if (!guest.getMovingQueue().isEmpty()) {
            guest.getMovingQueue().clear();
        }
        System.out.println(guest.getMovingQueue());

        Dijkstra dijkstra = new Dijkstra();
        guest.getArea().setDistanceForPerson(guest, 0);
        System.out.println(guest.getArea());
        LinkedList<Area> path = dijkstra.findPath(guest, guest.getArea(), lobby);
        Platform.runLater(()->guest.setMovingQueue(path));
        System.out.println(path);
        hotel.guestList.remove(guest);
        System.out.println(guest.getMovingQueue());

//        CleaningEvent cleaningEvent = new CleaningEvent(hotel.settings.getCleanHTE(), hotel, guestNumber, cleaningListeners);
//        hotel.cleaningEvents.add(cleaningEvent);
//        for (CleaningListener CleaningListener : cleaningListeners) {
//            CleaningListener.startCleaners();
//        }

        //TODO deregister guest from latecominghtelisteners.
    }
}

