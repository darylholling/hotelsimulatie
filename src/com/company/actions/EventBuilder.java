package com.company.actions;

import com.company.events.CheckInEvent;
import com.company.events.CleaningEmergencyEvent;
import com.company.events.EvacuateEvent;
import com.company.events.Event;
import com.company.models.CleaningListener;
import com.company.events.*;
import com.company.models.BuilderInterface;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.google.gson.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class EventBuilder {
    public static int highestHteInJsonFile;
    private File eventsFile;

    public EventBuilder(File eventFile) {
        this.eventsFile = eventFile;
    }

    public Queue<Event> readJson(Hotel hotel) throws IOException {
        this.hotel = hotel;
        eventsFile = new File("src/com/company/files/events3.json");
        Gson gson = new GsonBuilder().create();
        JsonArray eventJsonArray = gson.fromJson(Files.newBufferedReader(new File(String.valueOf(eventsFile)).toPath(), StandardCharsets.UTF_8), JsonArray.class);

        ArrayList<Event> eventsArray = new ArrayList<>();

        for (JsonElement jsonElement : eventJsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String eventType = jsonObject.get("type").getAsString();
            int eventTime = jsonObject.get("time").getAsInt();
            JsonObject data = jsonObject.get("data").getAsJsonObject();

            //TODO tycho herschreven
            if (eventTime > highestHteInJsonFile) {
                highestHteInJsonFile = eventTime;
            }

            int guestNumber = 0;
            int stars = 0;
            int duration = 0;

            if (data.has("guest")) {
                guestNumber = data.get("guest").getAsInt();
            }
            if (data.has("stars")) {
                stars = data.get("stars").getAsInt();
            }
            if (data.has("duration")) {
                duration = data.get("duration").getAsInt();
            }

            Event event = null;
            switch (eventType) {
                case "CHECK_IN":
                    event = new CheckInEvent(hotel, eventTime, guestNumber, stars);
                    break;
               case "CHECK_OUT":
                   event = new CheckOutEvent(hotel, eventTime, guestNumber, new ArrayList<>() {{
                       add(hotel.cleaners.get(0));
                       add(hotel.cleaners.get(1));
                   }});
                   break;
                case "GO_TO_DINER":
                    event = new GoToDinerEvent(eventTime, hotel, guestNumber);
                break;
                case "GO_TO_FITNESS":
                    event = new GoToFitnessEvent(eventTime, hotel, guestNumber, duration);
                break;
                case "CLEANING_EMERGENCY":
                    event = new CleaningEmergencyEvent(hotel, eventTime, guestNumber, new ArrayList<>() {{
                        add(hotel.cleaners.get(0));
                        add(hotel.cleaners.get(1));
                    }});
                    break;
//                case "GODZILLA":
////                    event = new GodzillaEvent(guestList, eventTime);
//                break;
                case "EVACUATE":
                    event = new EvacuateEvent(hotel, eventTime);
                    break;
                default:
                    System.out.println("No event");
            }

            if (event != null) {
                eventsArray.add(event);
            }
        }

        // Sort array
        eventsArray.sort(new SortEventsByTime());

        // Create queue
        return new PriorityQueue<>(eventsArray);
    }

    static class SortEventsByTime implements Comparator<Event> {
        public int compare(Event a, Event b) {
            return a.getEventTime() - b.getEventTime();
        }
    }
}


