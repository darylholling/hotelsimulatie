package com.company.events;

import com.company.actions.Dijkstra;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;
import com.company.models.areas.Cinema;
import javafx.application.Platform;

import java.util.LinkedList;

public class GoToCinemaEvent extends Event {
    private int guestNumber;

    public GoToCinemaEvent(Integer eventTime, Hotel hotel, int guestNumber) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
    }

    @Override
    public void fire() {
        if (hotel.getCinema() == null) {
            return;
        }
        movingPath(hotel.getGuestByNumber(guestNumber));
    }

    public void movingPath(Guest currentGuest) {
        if (currentGuest == null) {
            return;
        }

        if (currentGuest.getArea() != null) {
            currentGuest.getArea().removePerson(currentGuest);
        }

        Area[] allCinemas = this.hotel.areas.stream().filter(area -> area instanceof Cinema).toArray(Area[]::new);

        Area selectedCinema = null;
        LinkedList<Area> selectedPath = null;

        //find closest cinema
        int closestDistance = Integer.MAX_VALUE;
        for (Area movie : allCinemas) {
            Dijkstra dijkstra = new Dijkstra();
            currentGuest.getArea().setDistanceForPerson(currentGuest, 0);
            LinkedList<Area> currentPath = dijkstra.findPath(currentGuest, currentGuest.getArea(), movie);
            int distance = currentPath.size();
            if (closestDistance > distance) {
                closestDistance = distance;
                selectedCinema = movie;
                selectedPath = currentPath;
            }
        }

        if (selectedCinema != null) {
            selectedCinema.addPerson(currentGuest);
        }

        LinkedList<Area> finalSelectedPath = selectedPath;
        Platform.runLater(() -> currentGuest.setMovingQueue(finalSelectedPath));
    }
}