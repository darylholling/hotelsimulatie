package com.company.events;

import com.company.actions.Dijkstra;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;
import com.company.models.areas.Fitness;
import javafx.application.Platform;

import java.util.ArrayList;
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
//        movingPath(hotel.getGuestByNumber(guestNumber));
        movingPath(hotel.getFitness());

    }

    public void movingPath(Area destination){
        Guest currentGuest = hotel.getGuestByNumber(guestNumber);

        if (currentGuest == null) {
            return;
        }

        Dijkstra ds = new Dijkstra();
//        if(null == currentGuest) {
//            System.out.println("there is no guests!! "+ guestNumber);
//            return;
//        }
        currentGuest.getArea().setDistanceForPerson(currentGuest, 0);
        System.out.println("Guest number: "+currentGuest.getGuestNumber()+ " is walking to fitness");
        LinkedList<Area> path = ds.findPath(currentGuest, currentGuest.getArea(), destination);
//        System.out.println(path);
        destination.addPerson(currentGuest);
        Platform.runLater(()->currentGuest.setMovingQueue(path));
        System.out.println("Guest number: "+currentGuest.getGuestNumber()+  " is at fitness location X: "+currentGuest.getArea().getX()+" and Y: "+ currentGuest.getArea().getY());
    }



//    public void xmovingPath(Guest currentGuest) {
////        for (Area area : hotel.areas) {
////            area.setLatest(null);
////            area.setDistance(Integer.MAX_VALUE);
////        }
//        Dijkstra ds = new Dijkstra();
//        Area[] allFitness = this.hotel.areas.stream().filter(area -> area instanceof Fitness).toArray(Area[]::new);
//        System.out.println("Guest number: " + currentGuest.getGuestNumber() + " is walking to fitness");
//        currentGuest.getArea().setDistanceForPerson(currentGuest, 0);
//        Area selectedFitness = null;
//        int closestDistance = Integer.MAX_VALUE;
//        for (Area fitness : allFitness) {
//            int distance = (ds.findPath(currentGuest, currentGuest.getArea(), fitness)).size();
//            if (closestDistance > distance) {
//                closestDistance = distance;
//                selectedFitness = fitness;
//            } else {
//                return;
//            }
//        }
//        Area finalSelectedFitness = selectedFitness;
//        Platform.runLater(() -> currentGuest.setMovingQueue(ds.findPath(currentGuest, currentGuest.getArea(), finalSelectedFitness)));
//        selectedFitness.addPerson(currentGuest);
//        System.out.println("Guest number: " + currentGuest.getGuestNumber() + " is at fitness location X: " + currentGuest.getArea().getX() + " and Y: " + currentGuest.getArea().getY());
//
//    }
}

