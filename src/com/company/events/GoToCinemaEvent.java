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

    public GoToCinemaEvent(Hotel hotel, Integer eventTime, int guestNumber) {
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
        Dijkstra ds = new Dijkstra();
        currentGuest.getArea().setDistance(0);
        System.out.println("Guest number: "+currentGuest.getGuestNumber()+ " is walking to Cinema");
        LinkedList<Area> path = ds.findPath(currentGuest.getArea(), destination);
        System.out.println(path);
        destination.addPerson(currentGuest);
        currentGuest.setMovingQueue(path);
        System.out.println("Guest number: "+currentGuest.getGuestNumber()+  " is at cinema location X: "+currentGuest.getArea().getX()+" and Y: "+ currentGuest.getArea().getY());
    }
}
