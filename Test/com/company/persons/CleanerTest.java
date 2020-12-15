package com.company.persons;

import com.company.actions.EventHandler;
import com.company.events.CleaningEmergencyEvent;
import com.company.events.Event;
import com.company.models.Hotel;
import com.company.models.Settings;
import com.company.models.areas.GuestRoom;
import com.company.models.areas.Lobby;
import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Queue;

public class CleanerTest {
    private static Hotel hotel = new Hotel();
    private static EventHandler eventHandle = new EventHandler(hotel);
    private static Queue<Event> eventQueue;

    @BeforeAll
    public static void prepare() throws IOException {
        JFXPanel jfxPanel = new JFXPanel();
        Settings.getSettings().setEventsFile(new File("Test/jsonTestFiles/CleanerEventsTest.json"));
        eventHandle.handleStart();
        eventQueue = eventHandle.getEventsQueue();
        Lobby lobby = new Lobby(0, 0, 0, 0);
        lobby.setHotel(hotel);
        hotel.areas.add(lobby);
        GuestRoom guestRoom = new GuestRoom(0, 0, 1, 0, 1);
        guestRoom.setHotel(hotel);
        hotel.areas.add(guestRoom);
        triggerNextNormalEvent();
    }

    private static void triggerNextNormalEvent() {
        Objects.requireNonNull(eventQueue.poll()).fire();
    }

    private static void triggerNextEmergencyCleaningEvent() {
        triggerNextNormalEvent();
        Objects.requireNonNull(hotel.cleaningEmergencyEvents.poll()).fire();
    }

    @BeforeEach
    public void prepareForTest() {
        hotel.createCleaners();
    }

    @AfterEach
    public void reset() {
        hotel.cleaners.clear();
    }

    @Test
    public void checkIfCleanerPicksUpCleaningEvent() {
        triggerNextEmergencyCleaningEvent();
        startCleaner();

        Assert.assertNotNull(hotel.cleaners.get(0).getCurrentCleanEvent());
    }

    @Test
    public void checkIfCleanerPicksUpEmergencyCleaningEvent() {
        triggerNextEmergencyCleaningEvent();
        startCleaner();

        Assert.assertTrue((hotel.cleaners.get(0).getCurrentCleanEvent() instanceof CleaningEmergencyEvent));
    }

    private void startCleaner() {
        hotel.cleaners.get(0).startCleaners();
    }
}
