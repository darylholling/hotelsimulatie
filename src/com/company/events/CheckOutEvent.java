package com.company.events;

import com.company.listeners.CleaningListener;
import com.company.models.Hotel;
import com.company.models.Settings;
import com.company.models.areas.Area;
import com.company.persons.Guest;

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
    //handling fire event for current event
    public void fire() {
        Guest guest = this.hotel.getGuestByNumber(guestNumber);
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
        if (!guest.getMovingQueue().isEmpty()) {
            guest.getMovingQueue().clear();
        }

        guest.setMovingQueue(guest.determineShortestPath(hotel.getLobby()));

        DefaultCleaningEvent defaultCleaningEvent = new DefaultCleaningEvent(Settings.getSettings().getCleanHTE(), hotel, guestNumber, cleaningListeners);
        hotel.defaultCleaningEvents.add(defaultCleaningEvent);
        for (CleaningListener CleaningListener : cleaningListeners) {
            CleaningListener.startCleaners();
        }
    }
}

