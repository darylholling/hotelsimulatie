package com.company.events;

import com.company.actions.Dijkstra;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;

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
        System.out.println("firing cinema");
        Guest currentGuest = hotel.getGuestByNumber(guestNumber);
        System.out.println(guestNumber);
        System.out.println(currentGuest.getClass());
        System.out.println(currentGuest.getArea().getX() + ":" + currentGuest.getArea().getY());
//        this.hotel.guestList.remove(currentGuest);
        //todo lopen naar cinema
        //NOT TESTED
        Area cinema = hotel.getCinema();
        System.out.println("Guest number: "+currentGuest.getGuestNumber()+ " is walking to cinema");

        Dijkstra dijkstra = new Dijkstra();
        currentGuest.getArea().setDistance(0);
        String path = dijkstra.findPath(currentGuest, currentGuest.getArea(), cinema);
        System.out.println(path);
        System.out.println("Guest number: "+currentGuest.getGuestNumber()+  " is at cinema location X: "+currentGuest.getArea().getX()+" and Y: "+ currentGuest.getArea().getY());
        currentGuest.setArea(cinema);
        cinema.addPerson(currentGuest);
    }
}
