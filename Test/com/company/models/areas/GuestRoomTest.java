package com.company.models.areas;

import com.company.models.Guest;
import javafx.scene.image.ImageView;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class GuestRoomTest {

    @Test
    public void checkIfRoomIsOccupiedAndUnavailableAfterAddingGuest() throws FileNotFoundException {
        GuestRoom guestRoom = new GuestRoom(0, 0, 1, 0, 1);
        Guest guest = new Guest();
        guest.setGuestRoom(guestRoom);
        guestRoom.persons.add(guest);

        Assert.assertEquals(true, guestRoom.isOccupied());
        Assert.assertEquals(false, guestRoom.isAvailable());
    }

    @Test
    public void checkIfImageIsSetToNewRoom() throws FileNotFoundException {
        boolean hasImage = false;
        for (int i = 1; i <= 5; i++) {
            GuestRoom guestRoom = new GuestRoom(0, 0, 1, 1, i);
            if (guestRoom.getImageFile() != null) {
                hasImage = true;
            }
            Assert.assertEquals(true, hasImage);
        }
    }
}