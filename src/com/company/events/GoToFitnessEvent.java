package com.company.events;

import com.company.actions.Dijkstra;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;
import com.company.models.areas.Fitness;
import com.company.models.areas.GuestRoom;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

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

    LinkedList<Area> path = null;
    @Override
    public void fire() {
        movingPath(hotel.getGuestByNumber(guestNumber));

//        movingPath(hotel.getFitness());

    }

//    public void movingPath(Area destination){
//
//        for (Area area : hotel.areas) {
//            area.setLatest(null);
//            area.setDistance(Integer.MAX_VALUE);
//        }
//        Guest currentGuest = hotel.getGuestByNumber(guestNumber);
//        Dijkstra ds = new Dijkstra();
//        currentGuest.getArea().setDistance(0);
//        System.out.println("Guest number: "+currentGuest.getGuestNumber()+ " is walking to fitness");
//        LinkedList<Area> path = ds.findPath(currentGuest.getArea(), destination);
//        destination.addPerson(currentGuest);
//        Platform.runLater(()->currentGuest.setMovingQueue(path));
//        System.out.println("Guest number: "+currentGuest.getGuestNumber()+  " is at fitness location X: "+currentGuest.getArea().getX()+" and Y: "+ currentGuest.getArea().getY());
//    }
    public void movingPath(Guest currentGuest) {
        for (Area area : hotel.areas) {
            area.setLatest(null);
            area.setDistance(Integer.MAX_VALUE);
        }
        Dijkstra ds = new Dijkstra();
        Area[] allFitness = this.hotel.areas.stream().filter(area -> area instanceof Fitness).toArray(Area[]::new);
        System.out.println("Guest number: " + currentGuest.getGuestNumber() + " is walking to fitness");
        currentGuest.getArea().setDistance(0);
        Area selectedFitness = null;
        int closestDistance = Integer.MAX_VALUE;
        for (Area fitness : allFitness) {
            int distance = (ds.findPath(currentGuest.getArea(), fitness)).size();
            if (closestDistance > distance) {
                System.out.println("closest: "+ closestDistance);
                closestDistance = distance;
                System.out.println("closestNew: "+ closestDistance);
                selectedFitness = fitness;
            } else {
                return;
            }
        }
        Area finalSelectedFitness = selectedFitness;
        Platform.runLater(() -> currentGuest.setMovingQueue(ds.findPath(currentGuest.getArea(), finalSelectedFitness)));
        selectedFitness.addPerson(currentGuest);
        System.out.println("Guest number: " + currentGuest.getGuestNumber() + " is at fitness location X: " + currentGuest.getArea().getX() + " and Y: " + currentGuest.getArea().getY());

    }
}

