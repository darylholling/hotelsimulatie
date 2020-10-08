package com.company.events;

import com.company.actions.Dijkstra;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;
import com.company.models.areas.GuestRoom;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
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
        GuestRoom[] guestRooms = this.hotel.areas.stream().filter(area -> area instanceof GuestRoom).toArray(GuestRoom[]::new);
        GuestRoom[] availableByStars = null;

        while ((availableByStars != null ? availableByStars.length : 0) == 0 && this.stars < 5) {
            availableByStars = Arrays.stream(guestRooms).filter(guestRoom -> guestRoom.getStars() == this.stars && !guestRoom.isOccupied() && !guestRoom.needsCleaning()).toArray(GuestRoom[]::new);
            this.stars++;
        }

        //guest will not check in because nothing is available.
        if (availableByStars == null || availableByStars.length == 0) {
            return;
        }

        GuestRoom selectedGuestRoom = availableByStars[new Random().nextInt(availableByStars.length)];

        Guest guest = new Guest();
        guest.setGuestNumber(guestNumber);
        guest.setPreferredStars(stars);
        guest.setGuestRoom(selectedGuestRoom);
        guest.setArea(this.hotel.getLobby());
        selectedGuestRoom.addPerson(guest);
        //TODO add guest as HTEListener.

//        hotel.timer.stopTimer();
        hotel.timer.getHteCounter().addHTEListener(guest);
//        hotel.timer.startTimer();

        System.out.println(hotel.timer.getHteCounter().getHTElisteners().size());


//        //TODO dijkstra magic go to room with image visible.
        Dijkstra dijkstra = new Dijkstra();
        guest.getArea().setDistance(0);
        LinkedList<Area> path = dijkstra.findPath(guest.getArea(), guest.getGuestRoom());
        guest.setMovingQueue(path);
    }
}
