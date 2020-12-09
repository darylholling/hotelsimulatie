package com.company.actions;

import com.company.models.areas.GuestRoom;
import com.company.persons.Guest;
import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

class DijkstraTest {
    Dijkstra dijkstra = new Dijkstra();
    JFXPanel jfxPanel = new JFXPanel();


    @Test
    public void checkIfPathFindingRemovesAreasFromUnvisitedAndReachesEndArea() throws FileNotFoundException {
        Guest guest = new Guest();
        GuestRoom guestRoomStart = new GuestRoom(1, 1, 1, 1, 1);
        guest.setArea(guestRoomStart);
        guestRoomStart.setDistanceForPerson(guest, 0);
        GuestRoom guestRoomInBetween = new GuestRoom(2, 1, 1, 1, 1);
        GuestRoom guestRoomEnd = new GuestRoom(3, 1, 1, 1, 1);

        dijkstra.unvisitedAreas.addAll(Arrays.asList(guestRoomStart, guestRoomInBetween, guestRoomEnd));

        dijkstra.findPath(guest, guestRoomEnd);

        Assert.assertEquals(dijkstra.unvisitedAreas.get(0), guestRoomEnd);

    }
}