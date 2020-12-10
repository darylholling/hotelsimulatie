package com.company.persons;

import com.company.actions.EventBuilder;
import com.company.actions.EventHandler;
import com.company.events.Event;
import com.company.models.Hotel;
import com.company.models.Settings;
import com.company.models.areas.GuestRoom;
import com.company.models.areas.Lobby;
import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

public class CleanerTest {
    private static Hotel hotel = new Hotel();
    private static EventHandler eventHandle = new EventHandler(hotel);
    private static Queue<Event> eventQueue;


    @BeforeAll
    public static void prepareForTest() throws IOException {
        JFXPanel jfxPanel = new JFXPanel();
        Settings.getSettings().setEventsFile(new File("Test/jsonTestFiles/CleanerEventsTest.json"));
        eventHandle.handleStart();
        Lobby lobby = new Lobby(0, 0, 0, 0);
        hotel.areas.add(lobby);
        GuestRoom guestRoom = new GuestRoom(0, 0, 1, 0, 1);
        guestRoom.setHotel(hotel);
        hotel.areas.add(guestRoom);
        hotel.createCleaners();
        eventQueue = eventHandle.getEventsQueue();
        System.out.println(eventHandle.getEventsQueue());
        triggerNextEvent();
        triggerNextEvent();
    }

    @Test
    public void checkIfCleanerPicksUpCleaningEvent() {
        System.out.println(hotel.cleaningEmergencyEvents);
        Objects.requireNonNull(hotel.cleaningEmergencyEvents.poll()).fire();
        System.out.println(hotel.guestList);
        hotel.cleaners.get(0).startCleaners();
        System.out.println(hotel.cleaners.get(0).getCurrentCleanEvent());

        Assert.assertNotNull(hotel.cleaners.get(0).getCurrentCleanEvent());
    }

    private static void triggerNextEvent() {
        Objects.requireNonNull(eventQueue.poll()).fire();
    }
}
