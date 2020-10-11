package com.company.events;

import com.company.actions.Dijkstra;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;
import com.company.models.areas.Fitness;
import javafx.application.Platform;

import java.util.LinkedList;

public class GoToFitnessEvent extends Event {
    private int guestNumber;
    private int duration;

    public GoToFitnessEvent(Integer eventTime, Hotel hotel, int guestNumber, int duration) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.duration = duration;
    }

    @Override
    public void fire() {
        if (hotel.getFitness() == null) {
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
        Area[] allFitness = this.hotel.areas.stream().filter(area -> area instanceof Fitness).toArray(Area[]::new);
        Area selectedFitness = null;
        LinkedList<Area> selectedPath = null;
        int closestDistance = Integer.MAX_VALUE;
        for (Area fitness : allFitness) {
            Dijkstra dijkstra = new Dijkstra();
            currentGuest.getArea().setDistanceForPerson(currentGuest, 0);
            LinkedList<Area> currentPath = dijkstra.findPath(currentGuest, currentGuest.getArea(), fitness);
            int distance = currentPath.size();
            if (closestDistance > distance) {
                closestDistance = distance;
                selectedFitness = fitness;
                selectedPath = currentPath;
            }
        }
        if (selectedFitness != null) {
            selectedFitness.addPerson(currentGuest);
        }

        LinkedList<Area> finalSelectedPath = selectedPath;
        Platform.runLater(() -> currentGuest.setMovingQueue(finalSelectedPath));
    }
}

