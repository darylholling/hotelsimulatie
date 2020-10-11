package com.company.events;

import com.company.actions.Dijkstra;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;
import com.company.models.areas.Diner;
import javafx.application.Platform;

import java.util.LinkedList;

public class GoToDinerEvent extends Event {
    private int guestNumber;

    public GoToDinerEvent(Integer eventTime, Hotel hotel, int guestNumber) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
    }

    @Override
    public void fire() {
        if (hotel.getDiner() == null) {
            return;
        }

        determineAndSetMovingQueue(hotel.getGuestByNumber(guestNumber));
    }

    public void determineAndSetMovingQueue(Guest currentGuest) {
        if (currentGuest == null) {
            return;
        }
        if (currentGuest.getArea() != null) {
            currentGuest.getArea().removePerson(currentGuest);
        }
        Area[] allDiner = this.hotel.areas.stream().filter(area -> area instanceof Diner).toArray(Area[]::new);

        Area selectetDiner = null;
        LinkedList<Area> selectedPath = null;
        int closestDistance = Integer.MAX_VALUE;
        for (Area diner : allDiner) {
            Dijkstra dijkstra = new Dijkstra();
            currentGuest.getArea().setDistanceForPerson(currentGuest, 0);
            LinkedList<Area> currentPath = dijkstra.findPath(currentGuest, currentGuest.getArea(), diner);
            int distance = currentPath.size();
            if (closestDistance > distance) {
                closestDistance = distance;
                selectetDiner = diner;
                selectedPath = currentPath;
            }
        }
        if (selectetDiner != null) {
            selectetDiner.addPerson(currentGuest);
        }

        LinkedList<Area> finalSelectedPath = selectedPath;
        Platform.runLater(() -> currentGuest.setMovingQueue(finalSelectedPath));
    }
}
