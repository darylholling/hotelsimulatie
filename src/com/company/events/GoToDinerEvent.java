package com.company.events;

import com.company.actions.Dijkstra;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;
import com.company.models.areas.Diner;
import com.company.models.areas.GuestRoom;
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
        Guest currentGuest = hotel.getGuestByNumber(guestNumber);
        Area diner = hotel.getDiner();

        Dijkstra ds = new Dijkstra();
        currentGuest.getArea().setDistance(0);
        System.out.println("Guest number: "+currentGuest.getGuestNumber()+ " is walking to Diner");
        LinkedList<Area> dinerPath = ds.findPath(currentGuest.getArea(), diner);
        System.out.println(dinerPath);
        diner.addPerson(currentGuest);
        Platform.runLater(()->currentGuest.setMovingQueue(dinerPath));
        System.out.println("Guest number: "+currentGuest.getGuestNumber()+  " is at diner location X: "+currentGuest.getArea().getX()+" and Y: "+ currentGuest.getArea().getY());

    }
}
