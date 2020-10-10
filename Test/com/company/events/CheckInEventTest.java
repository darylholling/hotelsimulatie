package com.company.events;

import com.company.actions.HotelBuilder;
import com.company.models.Guest;
import com.company.models.Hotel;
import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CheckInEventTest {
    Hotel hotel = new Hotel();
    HotelBuilder hotelBuilder = new HotelBuilder(hotel);
    CheckInEvent checkInEvent = new CheckInEvent(hotel, 0, 1, 1);
    JFXPanel jfxPanel = new JFXPanel();

    @Test
    public void checkIfGuestIsAddedToHotelGuestListAfterCheckInEvent() throws IOException {

        boolean hasGuest = false;

        //check if guestList is empty before check-in
        for (int i = 0; i < hotel.guestList.size(); i++) {
            if (hotel.guestList.get(i) instanceof Guest) {
                hasGuest = true;
            }
        }
        Assert.assertFalse(hasGuest);

        //Fire check-in
        hotelBuilder.createContent();
        checkInEvent.fire();

        //check if guest is added after check-in
        for (int i = 0; i < hotel.guestList.size(); i++) {
            if (hotel.guestList.get(i) instanceof Guest) {
                hasGuest = true;
            }
        }
        Assert.assertTrue(hasGuest);

    }

}