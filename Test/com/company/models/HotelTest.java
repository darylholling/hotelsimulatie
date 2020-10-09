package com.company.actions;


import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;
import com.company.models.areas.GuestRoom;
import com.company.models.areas.Lobby;
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
    public void checkIfAreaStreamReturnsLobby() throws FileNotFoundException {
        areas.add(new GuestRoom(0, 0, 1, 1, 1));
        areas.add(new Lobby(1, 1, 1, 1));
        areas.add(new GuestRoom(1, 0, 1, 1, 1));

        hotel.areas = areas;

        Assert.assertEquals(areas.get(1), hotel.getLobby());
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