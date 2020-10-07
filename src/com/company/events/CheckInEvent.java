package com.company.events;

import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.GuestRoom;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;

public class CheckInEvent extends Event {
    private int id;
    private int stars;

    public CheckInEvent(Hotel hotel, Integer eventTime, int idGuest, int stars) {
        super(eventTime, hotel);
        this.id = idGuest;
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

        Guest guest = new Guest();
        guest.setId(id);
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
