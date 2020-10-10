package com.company.events;

import com.company.actions.Dijkstra;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;
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

    @Override
    public void fire() {
        movingPath(hotel.getFitness());
    }
    public void movingPath(Area destination){
        Guest currentGuest = hotel.getGuestByNumber(guestNumber);
        Dijkstra ds = new Dijkstra();
        currentGuest.getArea().setDistance(0);
        System.out.println("Guest number: "+currentGuest.getGuestNumber()+ " is walking to fitness");
        LinkedList<Area> path = ds.findPath(currentGuest.getArea(), destination);
        System.out.println(path);
        destination.addPerson(currentGuest);
        Platform.runLater(()->currentGuest.setMovingQueue(path));
        System.out.println("Guest number: "+currentGuest.getGuestNumber()+  " is at fitness location X: "+currentGuest.getArea().getX()+" and Y: "+ currentGuest.getArea().getY());
    }
}
