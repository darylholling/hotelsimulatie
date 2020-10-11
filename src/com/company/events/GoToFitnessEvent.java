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
    private Hotel hotel;

    public GoToFitnessEvent(Integer eventTime, Hotel hotel, int guestNumber, int duration) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.duration = duration;
        this.hotel = hotel;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public void setGuestNumber(int guestNumber) {
        this.guestNumber = guestNumber;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

   // LinkedList<Area> path = null;
    @Override
    public void fire() {
        movingPath(hotel.getGuestByNumber(guestNumber));
//        movingPath(hotel.getFitness());

    }

    public void movingPath(Guest currentGuest) {
        if (currentGuest == null) {
            return;
        }
        if (currentGuest.getArea()!=null){
            currentGuest.getArea().removePerson(currentGuest);
        }
        Area[] allFitness = this.hotel.areas.stream().filter(area -> area instanceof Fitness).toArray(Area[]::new);
        for (Area fit: allFitness){
            System.out.println(fit.getClass().getSimpleName());
        }
        System.out.println("Guest number: " + currentGuest.getGuestNumber() + " is walking to fitness");
        Area selectedFitness = null;
        LinkedList<Area> selectedPath = null;
        int closestDistance = Integer.MAX_VALUE;
        for (Area fitness : allFitness) {
            Dijkstra ds = new Dijkstra();
            currentGuest.getArea().setDistanceForPerson(currentGuest, 0);
            LinkedList<Area> currentPath = ds.findPath(currentGuest, currentGuest.getArea(), fitness);
            int distance = currentPath.size();
            if (closestDistance > distance) {
                closestDistance = distance;
                selectedFitness = fitness;
                selectedPath = currentPath;
            }
        }
        selectedFitness.addPerson(currentGuest);

        LinkedList<Area> finalSelectedPath = selectedPath;
        Platform.runLater(()->currentGuest.setMovingQueue(finalSelectedPath));
        System.out.println("Guest number: " + currentGuest.getGuestNumber() + " is at fitness location X: " + currentGuest.getArea().getX() + " and Y: " + currentGuest.getArea().getY());
    }
}

