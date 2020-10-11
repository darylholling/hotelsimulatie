package com.company.events;

import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Diner;
import com.company.models.areas.GuestRoom;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GoToDinerEventTest {
    Hotel hotel = new Hotel();


    @Test
    public void testIfGuestGoesToClosestDiner() throws FileNotFoundException {
        GuestRoom guestRoom = new GuestRoom(0, 0, 1, 1, 1);
        Diner closestDiner = new Diner(2, 2, 1, 1, 1);
        Diner furthestDiner = new Diner(3, 3, 1, 1, 2);
        hotel.areas.addAll(Arrays.asList(guestRoom,closestDiner,furthestDiner));
        Guest testGuest = new Guest();
        testGuest.setArea(guestRoom);

        testGuest.setGuestNumber(1);
        GoToDinerEvent goToDinerEvent = new GoToDinerEvent(0, hotel, 1);
        goToDinerEvent.fire();

        Assert.assertEquals(closestDiner,testGuest.getArea());
    }

}