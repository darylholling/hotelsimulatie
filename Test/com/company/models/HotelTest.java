package com.company.actions;


import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;
import com.company.models.areas.GuestRoom;
import com.company.models.areas.Lobby;
import javafx.scene.layout.GridPane;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class HotelTest {
    Hotel hotel = new Hotel();
    ArrayList<Area> areas = new ArrayList<>();

    @Test
    public void checkIfAreaStreamReturnsLobby() throws FileNotFoundException {
        areas.add(new GuestRoom(0,0,1,1,1));
        areas.add(new Lobby(1,1,1,1));
        areas.add(new GuestRoom(1,0,1,1,1));

        hotel.areas = areas;

        Assert.assertEquals(areas.get(1),hotel.getLobby());
    }
}