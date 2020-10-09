package com.company.events;

import com.company.actions.Dijkstra;
import com.company.actions.HotelBuilder;
import com.company.models.Guest;
import com.company.models.Hotel;
import javafx.application.Platform;

public class CheckOutEvent extends Event {
    private int guestNumber;
    private Guest guest;

    public CheckOutEvent(Hotel hotel, Integer eventTime, Guest guest, int guestNumber) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.hotel = hotel;
        this.guest = guest;

    }


    @Override
    public void fire() {
        //TODO lopen naar lobby
        if (guest.getGuestNumber() == guestNumber) {
            System.out.println("Guest number:  "+ guestNumber+ "is checking out");
            Dijkstra ds = new Dijkstra();
            String path = ds.findPath(this.guest, this.guest.getArea(), hotel.getLobby());
            Platform.runLater(() -> hotel.guestList.remove(guestNumber));
            Platform.runLater(() -> HotelBuilder.gridPane.getChildren().remove(this.guest.getArea().getX(), this.guest.getArea().getY()));
            System.out.println("Checkout path"+ path);
        }
        this.hotel.guestList.remove(guest);
        //TODO add cleaning to queue
    }
}

