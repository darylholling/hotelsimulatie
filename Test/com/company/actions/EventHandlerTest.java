package com.company.actions;

import com.company.events.CheckInEvent;
import com.company.events.CheckOutEvent;
import com.company.events.Event;
import com.company.models.Hotel;
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
    EventBuilder eventBuilder = new EventBuilder(new File("Test/jsonTestFiles/eventsTest.json"));

    @Test
    public void checkIfTheReadJSonCreatesTheEvents() throws IOException {
        JFXPanel jfxPanel = new JFXPanel();
        boolean correctEventType = false;

        //TODO Build check if all Events are Added to Application
        for (Event element : eventBuilder.readJson(hotel)){
            if (element instanceof CheckInEvent){
                correctEventType = true;
            } else if (element instanceof CheckOutEvent){
                correctEventType = true;
            } else {
                System.out.println("Events not added yet");
            }

        }

    }

}