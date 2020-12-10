package com.company.models.areas;

import com.company.models.Hotel;
import com.company.persons.Guest;
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
    public void checkIfGetLobbyReturnsNullIfNotExists() {
        Area lobby = hotel.getLobby();
        Assert.assertNull(lobby);
    }

    @Test
    public void checkIfGetLobbyReturnsLobbyIfExists() throws FileNotFoundException {
        Area lobby = new Lobby(1, 1, 1, 1);
        hotel.areas.add(lobby);

        Assert.assertTrue(hotel.getLobby() instanceof Lobby);
    }

    @Test
    public void checkIfHotelCreatesTwoCleaners() throws FileNotFoundException {
        Lobby lobby = new Lobby(1, 1, 1, 1);
        hotel.areas.add(lobby);
        hotel.createCleaners();
        Assert.assertEquals(2, hotel.cleaners.size());
    }

    @Test
    public void checkIfAreaForTypeReturnsProperType() throws FileNotFoundException {
        this.hotel.areas.add(new Diner(1, 1, 1, 1));
        this.hotel.areas.add(new Diner(1, 1, 1, 1));
        this.hotel.areas.add(new Cinema(1, 1, 1, 1));
        this.hotel.areas.add(new Cinema(1, 1, 1, 1));
        this.hotel.areas.add(new Fitness(1, 1, 1, 1));
        this.hotel.areas.add(new Fitness(1, 1, 1, 1));

        Area[] diners = hotel.getAreasForType("diner");

        for (Area area : diners) {
            Assert.assertTrue(area instanceof Diner);
        }

        Area[] cinemas = hotel.getAreasForType("cinema");

        for (Area area : cinemas) {
            Assert.assertTrue(area instanceof Cinema);
        }

        Area[] fitnesses = hotel.getAreasForType("fitness");

        for (Area area : fitnesses) {
            Assert.assertTrue(area instanceof Fitness);
        }
    }

    @Test
    public void checkIfAreaForTypeReturnsNullIfNoMatch() {
        Assert.assertNull(hotel.getAreasForType("failstring"));
    }

    @Test
    public void checkIfAreaForTypeReturnsEmptyArrayIfRequestedAreaDoesNotExist() {
        Area[] diners = hotel.getAreasForType("diner");

        Assert.assertEquals(diners.length, 0);
    }

    @Test
    public void checkIfAreaStreamReturnsCorrectTypeOfArea() throws FileNotFoundException {
        areas.add(new GuestRoom(0, 0, 1, 1, 1));
        areas.add(new Lobby(1, 1, 1, 1));
        areas.add(new Diner(2, 2, 1, 1));
        areas.add(new Cinema(3, 3, 1, 1));
        areas.add(new Fitness(3, 3, 1, 1));

        hotel.areas = areas;

        Assert.assertEquals(areas.get(1), hotel.getLobby());
        Assert.assertEquals(areas.get(2), hotel.getAreasForType("diner")[0]);
        Assert.assertEquals(areas.get(3), hotel.getAreasForType("cinema")[0]);
        Assert.assertEquals(areas.get(4), hotel.getAreasForType("fitness")[0]);
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