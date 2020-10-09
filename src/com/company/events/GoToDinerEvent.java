package com.company.events;

import com.company.actions.Dijkstra;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;

public class GoToDinerEvent extends Event {
    private int guestNumber;
    private Hotel hotel;

    public GoToDinerEvent(Integer eventTime, Hotel hotel, int guestNumber) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.hotel = hotel;
    }

    public int getIdGuest() {
        return guestNumber;
    }

    public void setIdGuest(int guestNumber) {
        this.guestNumber = guestNumber;
    }

    @Override
    public void fire() {
        System.out.println("firing diner");

        //        this.hotel.guestList.remove(hotel.getGuestByNumber(guestNumber));
        Guest currentGuest = hotel.getGuestByNumber(guestNumber);
//        this.hotel.guestList.remove(currentGuest);
        //todo lopen naar Diner
        //NOT TESTED
        Area diner = hotel.getDiner();
        System.out.println("Guest number: "+currentGuest.getGuestNumber()+ " is walking to Diner");
        Dijkstra ds = new Dijkstra();
//        currentGuest.getArea().setDistance(0);
        String path = ds.findPath(currentGuest, currentGuest.getArea(), diner);
        System.out.println(path);
//        String path = this.hotel.dijkstra.findPath(currentGuest, currentGuest.getArea(), diner);
        System.out.println("Guest number: "+currentGuest.getGuestNumber()+  " is at diner location X: "+currentGuest.getArea().getX()+" and Y: "+ currentGuest.getArea().getY());
        currentGuest.setArea(diner);
        diner.addPerson(currentGuest);
    }
}
