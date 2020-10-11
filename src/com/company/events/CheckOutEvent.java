package com.company.events;

import com.company.models.CleaningListener;
import com.company.models.Hotel;

import java.util.ArrayList;

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
        if (!guest.getMovingQueue().isEmpty()) {
            guest.getMovingQueue().clear();
        }

        Dijkstra dijkstra = new Dijkstra();
        guest.getArea().setDistanceForPerson(guest, 0);
        System.out.println(guest.getArea());
        LinkedList<Area> path = dijkstra.findPath(guest, guest.getArea(), lobby);
        Platform.runLater(()->guest.setMovingQueue(path));
        hotel.guestList.remove(guest);

        DefaultCleaningEvent defaultCleaningEvent = new DefaultCleaningEvent(hotel.settings.getCleanHTE(), hotel, guestNumber, cleaningListeners);
        hotel.defaultCleaningEvents.add(defaultCleaningEvent);
        for (CleaningListener CleaningListener : cleaningListeners) {
            CleaningListener.startCleaners();
        }
        //TODO deregister guest from latecominghtelisteners.
    }
}

