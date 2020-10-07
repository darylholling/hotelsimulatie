package com.company.events;

import com.company.actions.HotelBuilder;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.GuestRoom;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;

public class CheckInEvent extends Event {
    private int guestNumber;
    private int stars;

    public CheckInEvent(Hotel hotel, Integer eventTime, int guestNumber, int stars) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.stars = stars;
        this.hotel = hotel;
    }

    @Override
    public void fire() {
        //TODO check if room with stars is available, otherwise upgrade. If upgrade not possible create checkout event.

        GuestRoom[] guestRooms = this.hotel.areas.stream().filter(area -> area instanceof GuestRoom).toArray(GuestRoom[]::new);

        GuestRoom[] availableByStars = Arrays.stream(guestRooms).filter(guestRoom -> guestRoom.getStars() == this.stars && !guestRoom.isOccupied() && !guestRoom.needsCleaning()).toArray(GuestRoom[]::new);

        //TODO upgrade if no results
        GuestRoom selectedGuestRoom = availableByStars[new Random().nextInt(availableByStars.length)];
        System.out.println("Room Rating: "+selectedGuestRoom.getStars()+ " Location: X : "+selectedGuestRoom.getX()+" Y: " + selectedGuestRoom.getY());

        Guest guest = new Guest();
        guest.setGuestNumber(guestNumber);
        guest.setPreferredStars(stars);
        guest.setGuestRoom(selectedGuestRoom);
        guest.setArea(this.hotel.getLobby());
        this.hotel.guestList.add(guest);

        //TODO dijkstra magic go to room with image visible.
        guest.setVisible(true);
        this.hotel.dijkstra.findPath(guest.getArea(), guest.getGuestRoom());
        guest.setVisible(false);

        //TODO go to room
    }
}
