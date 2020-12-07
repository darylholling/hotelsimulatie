package com.company.actions;

import com.company.models.areas.GuestRoom;
import com.company.persons.Guest;
import javafx.application.Platform;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

class DijkstraTest {
    Dijkstra dijkstra = new Dijkstra();


    @Test
    public void checkIfPathFindingRemovesAreasFromUnvisitedAndReachesEndArea() throws FileNotFoundException {
        Guest guest = new Guest();
        GuestRoom guestRoomOne = new GuestRoom(1, 1, 1, 1, 1);
        guest.setArea(guestRoomOne);
        guestRoomOne.setDistanceForPerson(guest, 0);
        GuestRoom guestRoomTwo = new GuestRoom(2, 1, 1, 1, 1);
        GuestRoom guestRoomThree = new GuestRoom(3, 1, 1, 1, 1);
        GuestRoom guestRoomFour = new GuestRoom(1, 2, 1, 1, 1);
        GuestRoom guestRoomFive = new GuestRoom(2, 2, 2, 1, 4);
        GuestRoom guestRoomSix = new GuestRoom(1, 3, 1, 1, 1);
        GuestRoom guestRoomSeven = new GuestRoom(2, 3, 2, 1, 4);


        dijkstra.unvisitedAreas.addAll(Arrays.asList(guestRoomOne, guestRoomTwo, guestRoomThree, guestRoomFour, guestRoomFive, guestRoomSix, guestRoomSeven));

        dijkstra.findPath(guest, guestRoomSeven);

        Assert.assertEquals(dijkstra.unvisitedAreas.get(0), guestRoomSeven);

    }
}