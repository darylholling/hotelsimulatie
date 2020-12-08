package com.company.events;

import com.company.actions.HotelHandler;
import com.company.models.Hotel;
import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

class EventTest {
    private static Hotel hotel = new Hotel();
    private static HotelHandler hotelHandler = new HotelHandler(hotel);
    CheckInEvent checkInEvent = new CheckInEvent(hotel, 0, 1, 1);
    JFXPanel jfxPanel = new JFXPanel();

    @BeforeAll
    public static void prepareForTest() {
        hotelHandler.handleStart();
        JFXPanel jfxPanel = new JFXPanel();
    }


    @Test
    public void checkIfGuestIsAddedToHotelGuestListAfterCheckInEvent() throws IOException {

        boolean hasGuest = false;

        //check if guestList is empty before check-in
        for (int i = 0; i < hotel.activeGuestList.size(); i++) {
            if (hotel.activeGuestList.get(i) != null) {
                hasGuest = true;
            }
        }
        Assert.assertFalse(hasGuest);

        //Fire check-in
        checkInEvent.fire();

        //check if guest is added after check-in
        for (int i = 0; i < hotel.activeGuestList.size(); i++) {
            if (hotel.activeGuestList.get(i) != null) {
                hasGuest = true;
            }
        }
        Assert.assertTrue(hasGuest);

    }
    @Test
    public void checkIfGuestIsRemovedFromHotelGuestListAfterCheckOutEvent() throws FileNotFoundException {
        checkInEvent.fire();

        Assert.assertEquals(1, hotel.activeGuestList.size());

        CheckOutEvent checkOutEvent = new CheckOutEvent(hotel, 0, 1);

        checkOutEvent.fire();

        Assert.assertEquals(0, hotel.activeGuestList.size());
    }
}