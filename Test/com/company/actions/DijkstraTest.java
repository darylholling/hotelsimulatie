package com.company.actions;

import com.company.models.Guest;
import com.company.models.areas.Area;
import com.company.models.areas.GuestRoom;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {
    Dijkstra dijkstra = new Dijkstra();



    @Test
    public void checkIfPathFindingRemovesAreasFromUnvisitedAndReachesEndArea() throws FileNotFoundException {
        Guest guest = new Guest();
        GuestRoom guestRoomStart = new GuestRoom(1, 1, 1, 1, 1);
        guestRoomStart.setDistanceForPerson(guest,1);
        GuestRoom guestRoomInBetween = new GuestRoom(2, 1, 1, 1, 1);
        GuestRoom guestRoomEnd = new GuestRoom(3, 1, 1, 1, 1);


        dijkstra.unvisitedAreas.addAll(Arrays.asList(guestRoomStart, guestRoomInBetween, guestRoomEnd));

        dijkstra.findPath(guest,guestRoomStart, guestRoomEnd);

        Assert.assertEquals(dijkstra.unvisitedAreas.get(0), guestRoomEnd);

    }
}