package com.company.actions;

import com.company.models.Hotel;
import com.company.models.areas.Area;
import com.company.models.areas.GuestRoom;
import com.company.persons.Guest;
import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

class DijkstraTest {
    Dijkstra dijkstra = new Dijkstra();
    Hotel hotel = new Hotel();
    JFXPanel jfxPanel = new JFXPanel();


    @Test
    public void checkIfPathFindingRemovesAreasFromUnvisitedAndReachesEndArea() throws FileNotFoundException {
        Guest guest = new Guest();
        Area guestRoomOne = new GuestRoom(1, 1, 1, 1, 1);
        guest.setArea(guestRoomOne);
        guestRoomOne.setDistanceForPerson(guest, 0);
        Area guestRoomTwo = new GuestRoom(2, 1, 1, 1, 1);
        Area guestRoomThree = new GuestRoom(3, 1, 1, 1, 1);
        Area guestRoomFour = new GuestRoom(1, 2, 1, 1, 1);
        Area guestRoomFive = new GuestRoom(2, 2, 2, 1, 4);
        Area guestRoomSix = new GuestRoom(1, 3, 1, 1, 1);
        Area guestRoomSeven = new GuestRoom(2, 3, 2, 1, 4);

        hotel.areas.add(guestRoomOne);
        hotel.areas.add(guestRoomTwo);
        hotel.areas.add(guestRoomThree);
        hotel.areas.add(guestRoomFour);
        hotel.areas.add(guestRoomFive);
        hotel.areas.add(guestRoomSix);
        hotel.areas.add(guestRoomSeven);
        guestRoomSeven.setHotel(hotel);


        dijkstra.unvisitedAreas.addAll(Arrays.asList(guestRoomOne, guestRoomTwo, guestRoomThree, guestRoomFour, guestRoomFive, guestRoomSix, guestRoomSeven));

        dijkstra.findPath(guest, guestRoomSeven);

        Assert.assertEquals(dijkstra.unvisitedAreas.get(0), guestRoomSeven);

    }
}