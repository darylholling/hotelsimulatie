package com.company.events;

import com.company.models.Hotel;
import com.company.models.areas.GuestRoom;
import com.company.persons.Guest;
import javafx.application.Platform;

import java.util.Arrays;
import java.util.Random;

public class CheckInEvent extends Event {
    private int guestNumber;
    private int stars;

    public CheckInEvent(Hotel hotel, Integer eventTime, int guestNumber, int stars) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.stars = stars;
    }

    @Override
    //handles the notification received from FireableListener
    public void fire() {
        GuestRoom[] guestRooms = this.hotel.areas.stream().filter(area -> area instanceof GuestRoom).toArray(GuestRoom[]::new);
        GuestRoom[] availableByStars = null;

        //checks if there is a room available with preferred stars, if not then upgrade
        while ((availableByStars != null ? availableByStars.length : 0) == 0 && this.stars <= 5) {
            availableByStars = Arrays.stream(guestRooms).filter(guestRoom -> guestRoom.getStars() == this.stars && guestRoom.isAvailable()).toArray(GuestRoom[]::new);
            this.stars++;
        }

        //guest will not check in because nothing is available.
        if (availableByStars == null || availableByStars.length == 0) {
            return;
        }

        GuestRoom selectedGuestRoom = availableByStars[new Random().nextInt(availableByStars.length)];
        //add guest to selected guest room
        Guest guest = new Guest();
        guest.setGuestNumber(this.guestNumber);
        guest.setGuestRoom(selectedGuestRoom);
        Platform.runLater(() -> guest.setGuestImage());
        guest.setArea(this.hotel.getLobby());
        guest.setCheckInTime(eventTime);
        selectedGuestRoom.addPerson(guest);

        Platform.runLater(() -> hotel.lateComingHTEListeners.add(guest));
        hotel.addGuestToBothLists(guest);

        guest.setMovingQueue(guest.determineShortestPath(guest.getGuestRoom()));
        System.out.println("check in " + guestNumber);
    }
}
