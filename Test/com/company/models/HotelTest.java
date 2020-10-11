package com.company.models.areas;


import com.company.actions.HotelBuilder;
import com.company.models.*;
import com.company.models.areas.*;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;


public class HotelTest {
    Hotel hotel = new Hotel();
    ArrayList<Area> areas = new ArrayList<>();
    ArrayList<Guest> guestList = new ArrayList<>();

    @Test
    public void checkIfAreaStreamReturnsCorrectTypeOfArea() throws FileNotFoundException {
        areas.add(new GuestRoom(0, 0, 1, 1, 1));
        areas.add(new Lobby(1, 1, 1, 1));
        areas.add(new Diner(2, 2, 1, 1, 1));
        areas.add(new Cinema(3, 3, 1, 1));

        hotel.areas = areas;

        Assert.assertEquals(areas.get(1), hotel.getLobby());
        Assert.assertEquals(areas.get(2), hotel.getDiner());
        Assert.assertEquals(areas.get(3), hotel.getCinema());
    }

    @Test
    public void checkIfGuestStreamReturnsGuestByID() {

        Guest guest1 = new Guest();
        guest1.setGuestNumber(1);
        Guest guest2 = new Guest();
        guest2.setGuestNumber(15);
        Guest guest3 = new Guest();
        guest3.setGuestNumber(40);

        guestList.addAll(Arrays.asList(guest1, guest2, guest3));

        hotel.guestList = guestList;

        Assert.assertEquals(guestList.get(2), hotel.getGuestByNumber(40));
    }

}