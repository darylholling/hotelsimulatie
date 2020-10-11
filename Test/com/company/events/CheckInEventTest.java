package com.company.events;

import com.company.actions.HotelBuilder;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.Settings;
import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    Hotel hotel = new Hotel();
    HotelBuilder hotelBuilder = new HotelBuilder(hotel);
    CheckInEvent checkInEvent = new CheckInEvent(hotel, 0, 1, 1);
    JFXPanel jfxPanel = new JFXPanel();

    @BeforeAll
    public static void prepareForTest() {
        Settings.getSettings().setLayoutFile(new File("Test/jsonTestFiles/layoutTest.json"));
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
        hotelBuilder.createContent();
        checkInEvent.fire();

        //check if guest is added after check-in
        for (int i = 0; i < hotel.activeGuestList.size(); i++) {
            if (hotel.activeGuestList.get(i) != null) {
                hasGuest = true;
            }
        }
        Assert.assertTrue(hasGuest);

    }

    public void checkIfGuestIsRemovedFromHotelGuestListAfterCheckOutEvent() throws FileNotFoundException {
        hotelBuilder.createContent();
        checkInEvent.fire();

        Assert.assertEquals(1,hotel.activeGuestList.size());

        CheckOutEvent checkOutEvent = new CheckOutEvent(hotel,0,1,null);

        checkOutEvent.fire();

        Assert.assertEquals(0, hotel.activeGuestList.size());
    }
}