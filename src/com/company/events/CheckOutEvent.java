package com.company.events;

import com.company.actions.Dijkstra;
import com.company.actions.HotelBuilder;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;
import javafx.application.Platform;

import java.util.LinkedList;

public class CheckOutEvent extends Event {
    private int guestNumber;
    private Guest guest;
    public CheckOutEvent(Hotel hotel, Integer eventTime, int guestNumber) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.hotel = hotel;
    }


    @Override
    public void fire() {
        guest = hotel.getGuestByNumber(guestNumber);
        guest.getGuestRoom().removePerson(guest);
        hotel.guestList.remove(guest);
        Dijkstra dijkstra = new Dijkstra();
        LinkedList<Area> path = dijkstra.findPath(guest.getArea(), hotel.getLobby());
        System.out.println(path);
        Platform.runLater(()->guest.setMovingQueue(path));
    }
}

