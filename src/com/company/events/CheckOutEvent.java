package com.company.events;

import com.company.actions.Dijkstra;
import com.company.actions.HotelBuilder;
import com.company.models.CleaningListener;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.LinkedList;

public class CheckOutEvent extends Event {
    private ArrayList<CleaningListener> cleaningListeners;
    private int guestNumber;
    private Guest guest;
    private Hotel hotel;

    public CheckOutEvent(Hotel hotel, Integer eventTime, int guestNumber, ArrayList<CleaningListener> CleaningListener) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.hotel = hotel;
        this.cleaningListeners = CleaningListener;
    }


    @Override
    public void fire() {
        //TODO lopen naar lobby
        if (guest.getGuestNumber() == guestNumber) {
            System.out.println("Guest number:  "+ guestNumber+ "is checking out");
            Dijkstra ds = new Dijkstra();
            LinkedList<Area> path = ds.findPath(this.guest.getArea(), hotel.getLobby());
            Platform.runLater(() -> hotel.guestList.remove(guestNumber));
            Platform.runLater(() -> HotelBuilder.gridPane.getChildren().remove(this.guest.getArea().getX(), this.guest.getArea().getY()));
            System.out.println("Checkout path"+ path);


        CleaningEvent cleaningEvent = new CleaningEvent(hotel.settings.getCleanHTE(), hotel, guestNumber, cleaningListeners);
        hotel.cleaningEvents.add(cleaningEvent);
        for (CleaningListener CleaningListener : cleaningListeners) {
            CleaningListener.startCleaners();
        }
        this.hotel.guestList.remove(guest);
    }
}

