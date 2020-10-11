package com.company.actions;

import com.company.events.*;
import com.company.models.Hotel;
import com.company.models.Settings;
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
    //reading json file and creating event queue from it.
    public Queue<Event> readJson(Hotel hotel) {
        File eventsFile = Settings.getSettings().getEventsFile();
        Gson gson = new GsonBuilder().create();

        JsonArray eventJsonArray;
        try {
            eventJsonArray = gson.fromJson(Files.newBufferedReader(new File(String.valueOf(eventsFile)).toPath(), StandardCharsets.UTF_8), JsonArray.class);
        } catch (IOException | JsonParseException e) {
            hotel.timer.stopTimer();
            hotel.menu.addJsonError("eventsfile");
            hotel.menu.changeScene("loadFilePage");
            return null;
        }

        ArrayList<Event> eventsArray = new ArrayList<>();

        for (JsonElement jsonElement : eventJsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String eventType = jsonObject.get("type").getAsString();
            int eventTime = jsonObject.get("time").getAsInt();
            JsonObject data = jsonObject.get("data").getAsJsonObject();

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

            //creating events based on eventtype string.
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
                case "GO_TO_CINEMA":
                    event = new GoToCinemaEvent(eventTime, hotel, guestNumber);
                    break;
                case "START_CINEMA":
                    event = new StartCinemaEvent(eventTime, hotel, duration);
                    break;
                //                case "GODZILLA":
                //                    event = new GodzillaEvent(guestList, eventTime);
                //                break;
                case "EVACUATE":
                    event = new EvacuateEvent(hotel, eventTime);
                    break;
                default:
                    System.out.println("Event type " + eventType + " wasn't found.");
            }

            if (event != null) {
                eventsArray.add(event);
            }
        }

        // Sort array
        eventsArray.sort(new SortEventsByTime());

        // Determine the highest HTE
        Settings.getSettings().setHighestHteInJsonFile(eventsArray.stream().reduce((first, second) -> second).orElse(null).getEventTime());

        // Create queue
        return new PriorityQueue<>(eventsArray);
    }

    static class SortEventsByTime implements Comparator<Event> {
        public int compare(Event a, Event b) {
            return a.getEventTime() - b.getEventTime();
        }
    }
}


