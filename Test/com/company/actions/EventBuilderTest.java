package com.company.actions;

import com.company.events.*;
import com.company.models.Hotel;
import com.company.models.Settings;
import com.google.gson.JsonArray;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.GridPane;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class EventHandlerTest {
    Hotel hotel = new Hotel();
    EventBuilder eventBuilder = new EventBuilder();
    Settings settings = new Settings();

    @Test
    public void checkIfEventsGetCreated() throws IOException {
        JFXPanel jfxPanel = new JFXPanel();
        boolean correctEventType = false;

        settings.setEventsFile(new File("Test/jsonTestFiles/events3Test.json"));
        eventBuilder.readJson(hotel);

        //TODO Build check if all Events are Added to Application

        //instanceof is not switchable
        for (Event element : eventBuilder.readJson(hotel)) {
            System.out.println(element.getClass());
            if (element instanceof CheckInEvent) {
                correctEventType = true;
//            } else if (element instanceof CheckOutEvent) {
//                correctEventType = true;
//            } else if (element instanceof GoToCinemaEvent) {
//                correctEventType = true;
//            } else if (element instanceof GoToDinerEvent) {
//                correctEventType = true;
//            } else if (element instanceof GoToFitnessEvent) {
//                correctEventType = true;
            } else if (element instanceof CleaningEmergencyEvent) {
                correctEventType = true;
            } else if (element instanceof CleaningEvent){
                correctEventType = true;
            } else if (element instanceof GodzillaEvent){
                correctEventType = true;
            } else if (element instanceof EvacuateEvent){
                correctEventType = true;
            } else {
                System.out.println("Test failed. Event unknown and not created.");
            }

        }
        Assert.assertTrue(correctEventType);
    }

}