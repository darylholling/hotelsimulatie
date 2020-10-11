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
    private Hotel hotel;
    public GoToCinemaEvent(Integer eventTime, Hotel hotel, int guestNumber) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.hotel = hotel;
    }

    @Override
    public void fire() {
        if (hotel.getCinema() ==null){
            return;
        }
        movingPath(hotel.getGuestByNumber(guestNumber));
    }
    public void movingPath(Guest currentGuest) {
        if (currentGuest == null) {
            return;
        }
        if (currentGuest.getArea()!=null){
            currentGuest.getArea().removePerson(currentGuest);
        }
        Area[] allCinemas = this.hotel.areas.stream().filter(area -> area instanceof Cinema).toArray(Area[]::new);
        for (Area cinema: allCinemas){
            System.out.println(cinema.getClass().getSimpleName());
        }
        System.out.println("Guest number: " + currentGuest.getGuestNumber() + " is walking to cinema");
        Area selectedCinema = null;
        LinkedList<Area> selectedPath = null;
        int closestDistance = Integer.MAX_VALUE;
        for (Area movie : allCinemas) {
            Dijkstra ds = new Dijkstra();
            currentGuest.getArea().setDistanceForPerson(currentGuest, 0);
            LinkedList<Area> currentPath = ds.findPath(currentGuest, currentGuest.getArea(), movie);
            int distance = currentPath.size();
            if (closestDistance > distance) {
                closestDistance = distance;
                selectedCinema = movie;
                selectedPath = currentPath;
            }
        }
        selectedCinema.addPerson(currentGuest);

        LinkedList<Area> finalSelectedPath = selectedPath;
        Platform.runLater(()->currentGuest.setMovingQueue(finalSelectedPath));
        System.out.println("Guest number: " + currentGuest.getGuestNumber() + " is at cinema location X: " + currentGuest.getArea().getX() + " and Y: " + currentGuest.getArea().getY());
    }
}
