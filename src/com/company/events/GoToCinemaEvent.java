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
        System.out.println("firing cinema");
        Guest currentGuest = hotel.getGuestByNumber(guestNumber);
        System.out.println(guestNumber);
        System.out.println(currentGuest.getArea().getX() + ":" + currentGuest.getArea().getY());
//        hotel.guestList.remove(currentGuest);
        //todo lopen naar cinema
        //NOT TESTED
        Area cinema = hotel.getCinema();
        System.out.println("Guest number: "+currentGuest.getGuestNumber()+ " is walking to cinema");

        Dijkstra dijkstra = new Dijkstra();
        LinkedList<Area> path = dijkstra.findPath(currentGuest.getArea(), cinema);
        System.out.println(path);
        System.out.println("Guest number: "+currentGuest.getGuestNumber()+  " is at cinema location X: "+currentGuest.getArea().getX()+" and Y: "+ currentGuest.getArea().getY());
        cinema.addPerson(currentGuest);
        Platform.runLater(()->currentGuest.setMovingQueue(path));
    }
}
