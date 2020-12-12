package com.company.events;

import com.company.actions.*;
import com.company.models.Hotel;
import com.company.models.Settings;
import com.company.models.areas.Area;
import com.company.models.areas.Fitness;
import com.company.models.areas.GuestRoom;
import com.company.models.areas.Lobby;
import com.company.persons.Guest;
import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

class EventsTest {
    private Hotel hotel = new Hotel();
    private HotelBuilder hotelBuilder = new HotelBuilder(hotel);
    private CheckInEvent checkInEvent = new CheckInEvent(hotel, 0, 1, 1);
    Guest guest = new Guest();
    JFXPanel jfxPanel = new JFXPanel();

    @BeforeAll
    public static void prepareForTest() {
        Settings.getSettings().setLayoutFile(new File("Test/jsonTestFiles/layoutEventsTest.json"));
    }

    @Test
    public void checkIfGuestIsAddedToHotelGuestListAfterCheckInEvent() throws FileNotFoundException {
        boolean hasGuest = false;

        if (hotel.activeGuestList.size() > 0) {
            hasGuest = true;
        }

        Assert.assertFalse(hasGuest);
        hotelBuilder.createContent();
        checkInEvent.fire();

        if (hotel.activeGuestList.size() > 0) {
            hasGuest = true;
        }

        Assert.assertTrue(hasGuest);
    }
    public void standardCheckingInEvent() throws FileNotFoundException {
        hotelBuilder.createContent();
        checkInEvent.fire();
        guest = hotel.getGuestByNumber(1);
        guest.setArea(guest.getGuestRoom());
    }
    @Test
    public void checkIfGuestChoosesPathToClosestFacilities() throws FileNotFoundException {
        standardCheckingInEvent();
        GoToDinerEvent goToDinerEvent = new GoToDinerEvent(0, hotel, 1);
        goToDinerEvent.fire();
        Assert.assertEquals(17, guest.getMovingQueue().size());
        GoToFitnessEvent goToFitnessEvent = new GoToFitnessEvent(0, hotel, 1);
        goToFitnessEvent.fire();
        Assert.assertEquals(12, guest.getMovingQueue().size());
        GoToCinemaEvent goToCinemaEvent = new GoToCinemaEvent(0, hotel, 1);
        goToCinemaEvent.fire();
        Assert.assertEquals(14, guest.getMovingQueue().size());
    }
    @Test
    public void checkIfGuestGoToLobbyAfterEvacuateEventIsTriggered() throws FileNotFoundException {
        standardCheckingInEvent();
        EvacuateEvent evacuateEvent = new EvacuateEvent(hotel, 0);
        evacuateEvent.fire();
        Assert.assertEquals("Lobby", guest.getMovingQueue().getLast().getClass().getSimpleName());

    }

}
