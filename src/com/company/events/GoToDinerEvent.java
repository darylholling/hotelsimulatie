package com.company.events;

import com.company.actions.Dijkstra;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;
import javafx.application.Platform;
import java.util.LinkedList;

public class GoToDinerEvent extends Event {
    private int guestNumber;
    private Hotel hotel;

    public GoToDinerEvent(Integer eventTime, Hotel hotel, int guestNumber) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.hotel = hotel;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public void setGuestNumber(int guestNumber) {
        this.guestNumber = guestNumber;
    }

    @Override
    public void fire() {
    movingPath(hotel.getDiner());

    }
    public void movingPath(Area destination){

        Guest currentGuest = hotel.getGuestByNumber(guestNumber);

        if (currentGuest == null) {
            return;
        }
        Dijkstra ds = new Dijkstra();
        currentGuest.getArea().setDistanceForPerson(currentGuest, 0);
        //System.out.println("Guest number: "+currentGuest.getGuestNumber()+ " is walking to Diner");
        LinkedList<Area> path = ds.findPath(currentGuest, currentGuest.getArea(), destination);
        destination.addPerson(currentGuest);
        Platform.runLater(()->currentGuest.setMovingQueue(path));
        //System.out.println("Guest number: "+currentGuest.getGuestNumber()+  " is at diner location X: "+currentGuest.getArea().getX()+" and Y: "+ currentGuest.getArea().getY());

    }
}
