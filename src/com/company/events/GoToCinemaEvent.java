package com.company.events;

import com.company.actions.Dijkstra;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;
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
    movingPath(hotel.getCinema());
    }
    public void movingPath(Area destination){
        Guest currentGuest = hotel.getGuestByNumber(guestNumber);
        if (currentGuest == null) {
            return;
        }
        Dijkstra ds = new Dijkstra();
        currentGuest.getArea().setDistanceForPerson(currentGuest, 0);
        System.out.println("Guest number: "+currentGuest.getGuestNumber()+ " is walking to Cinema");
        LinkedList<Area> path = ds.findPath(currentGuest, currentGuest.getArea(), destination);
        System.out.println(path);
        destination.addPerson(currentGuest);
        Platform.runLater(()->currentGuest.setMovingQueue(path));
        System.out.println("Guest number: "+currentGuest.getGuestNumber()+  " is at cinema location X: "+currentGuest.getArea().getX()+" and Y: "+ currentGuest.getArea().getY());
    }
}
