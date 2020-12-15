package com.company.events;

import com.company.actions.HotelBuilder;
import com.company.models.Hotel;
import com.company.models.Settings;
import com.company.models.areas.GuestRoom;
import com.company.models.areas.Lobby;
import com.company.persons.Guest;
import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

class EventsTest {
    Guest guest = new Guest();
    JFXPanel jfxPanel = new JFXPanel();
    private Hotel hotel = new Hotel();
    private HotelBuilder hotelBuilder = new HotelBuilder(hotel);
    private CheckInEvent checkInEvent = new CheckInEvent(hotel, 0, 1, 1);

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
    public void checkoutEvent() throws FileNotFoundException {
        this.standardCheckingInEvent();

        Guest guest = hotel.getGuestByNumber(1);
        guest.setArea(guest.getMovingQueue().get(guest.getMovingQueue().size() - 1));
        guest.setGuestRoom((GuestRoom) guest.getMovingQueue().get(guest.getMovingQueue().size() - 1));

        CheckOutEvent checkOutEvent = new CheckOutEvent(this.hotel, 0, 1);
        checkOutEvent.fire();

        Assert.assertFalse(guest.getMovingQueue().isEmpty());

        while (!guest.getMovingQueue().isEmpty()) {
            guest.move(guest.getMovingQueue().get(0), guest.getMovingQueue().get(1));
        }
    }

    @Test
    public void checkIfActiveGuestlistIsEmptyAfterCheckout() throws FileNotFoundException {
        this.checkoutEvent();

        Assert.assertTrue(hotel.activeGuestList.isEmpty());
    }

    @Test
    public void checkIfMovingQueueIsEmptyAfterCheckout() throws FileNotFoundException {
        this.checkoutEvent();

        Assert.assertTrue(guest.getMovingQueue().isEmpty());
    }

    @Test
    public void checkIfGuestAreaIsLobbyAfterCheckout() throws FileNotFoundException {
        this.checkoutEvent();

        Assert.assertTrue(guest.getArea() instanceof Lobby);
    }

    @Test
    public void checkIfCleaningEventIsCreatedAfterCheckout() throws FileNotFoundException {
        this.checkoutEvent();

        Assert.assertFalse(hotel.defaultCleaningEvents.isEmpty());
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
