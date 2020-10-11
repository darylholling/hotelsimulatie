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
        GuestRoom guestRoomStart = new GuestRoom(1, 1, 1, 1, 1);
        guestRoomStart.setDistance(1);
        GuestRoom guestRoomInBetween = new GuestRoom(2, 1, 1, 1, 1);
        GuestRoom guestRoomEnd = new GuestRoom(3, 1, 1, 1, 1);


        dijkstra.unvisitedAreas.addAll(Arrays.asList(guestRoomStart, guestRoomInBetween, guestRoomEnd));

        dijkstra.findPath(guestRoomStart, guestRoomEnd);

        Assert.assertEquals(dijkstra.unvisitedAreas.get(0), guestRoomEnd);

    }
}