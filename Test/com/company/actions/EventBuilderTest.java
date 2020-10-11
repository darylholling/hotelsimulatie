package com.company.actions;

import com.company.events.*;
import com.company.models.Hotel;
import com.company.models.Settings;
import com.company.models.areas.Lobby;
import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Queue;

class EventHandlerTest {
    Hotel hotel = new Hotel();
    EventBuilder eventBuilder = new EventBuilder();

    @Test
    public void checkIfEventsGetCreated() throws IOException {
        JFXPanel jfxPanel = new JFXPanel();
        Lobby lobby = new Lobby(0, 0, 0, 0);
        hotel.areas.add(lobby);
        CreateCleaners createCleaners = new CreateCleaners(hotel);
        createCleaners.create();
        int checkCounter = 0;


        Settings.getSettings().setEventsFile(new File("Test/jsonTestFiles/eventsTest.json"));

        //instanceof is not switchable
        for (Event element : eventBuilder.readJson(hotel)) {


            if (element instanceof CheckInEvent) {
                checkCounter++;
            } else if (element instanceof CheckOutEvent) {
                checkCounter++;
            } else if (element instanceof GoToCinemaEvent) {
                checkCounter++;
            } else if (element instanceof GoToDinerEvent) {
                checkCounter++;
            } else if (element instanceof GoToFitnessEvent) {
                checkCounter++;
            } else if (element instanceof CleaningEmergencyEvent) {
                checkCounter++;
            } else if (element instanceof EvacuateEvent) {
                checkCounter++;
            } else {
                System.out.println("Test failed. Event unknown and not created.");
            }

        }
        Assert.assertEquals(7,checkCounter);
    }

}