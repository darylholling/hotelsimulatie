package com.company.events;

import com.company.actions.Dijkstra;
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
    private Integer eventTime;


    public CheckInEvent(Hotel hotel, Integer eventTime, int guestNumber, int stars) {
        super(eventTime, hotel);
        this.guestNumber = guestNumber;
        this.stars = stars;
        this.hotel = hotel;
        this.eventTime = eventTime;
    }

    @Override
    public void fire() {

        GuestRoom[] guestRooms = this.hotel.areas.stream().filter(area -> area instanceof GuestRoom).toArray(GuestRoom[]::new);

        GuestRoom[] availableByStars = Arrays.stream(guestRooms).filter(guestRoom -> guestRoom.getStars() == this.stars && !guestRoom.isOccupied() && !guestRoom.needsCleaning()).toArray(GuestRoom[]::new);
        // upgrade when preferred stars not available

        int freeRoom = 0;
        for (GuestRoom room : availableByStars){
            if (!room.isOccupied()){
                freeRoom ++;
            }
        }
        if (freeRoom == 0 && this.stars <5){
            this.stars++;
            availableByStars = Arrays.stream(guestRooms).filter(guestRoom -> guestRoom.getStars() == this.stars && !guestRoom.isOccupied() && !guestRoom.needsCleaning()).toArray(GuestRoom[]::new);
        }
        else {
            //TODO do not check in .. do we even need a checkoutevent?
        }


        GuestRoom selectedGuestRoom = availableByStars[new Random().nextInt(availableByStars.length)];
        System.out.println("Room Rating: "+selectedGuestRoom.getStars()+ " Location: X : "+selectedGuestRoom.getX()+" Y: " + selectedGuestRoom.getY());
        Guest guest = new Guest();
        guest.setGuestNumber(guestNumber);
        guest.setPreferredStars(stars);
        guest.setGuestRoom(selectedGuestRoom);
        guest.setArea(this.hotel.getLobby());
        selectedGuestRoom.addPerson(guest);
        System.out.println(selectedGuestRoom.isOccupied());

        //TODO dijkstra magic go to room with image visible.
        guest.setShown(true);
        guest.toFront();
        guest.getArea().setDistance(0);
        String path = this.hotel.dijkstra.findPath(guest, guest.getArea(), guest.getGuestRoom());
        //        System.out.println(guest.getArea());
        System.out.println(path);
//        guest.setVisible(false);
        this.hotel.guestList.add(guest);
            System.out.println(HotelBuilder.gridPane.getChildren().getClass().getSimpleName());

        //TODO go to room
    }
}
