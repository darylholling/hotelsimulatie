package com.company.actions;

import com.company.events.Event;
import com.company.models.Hotel;
import com.company.models.Settings;
import com.company.models.areas.Lobby;
import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class EventHandlerTest {
    private Hotel hotel = new Hotel();
    private EventBuilder eventBuilder = new EventBuilder();

    @Test
    public void checkIfEventsGetCreatedAfterReadingJson() throws IOException {
        JFXPanel jfxPanel = new JFXPanel();
        Lobby lobby = new Lobby(0, 0, 0, 0);
        hotel.areas.add(lobby);
        hotel.createCleaners();
        int checkCounter = 0;

        Settings.getSettings().setEventsFile(new File("Test/jsonTestFiles/eventsTest.json"));

        for (Event element : eventBuilder.readJson(hotel)) {
            if (element != null) {
                checkCounter++;
            }
        }

        Assert.assertEquals(7, checkCounter);
    }
}