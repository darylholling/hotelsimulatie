package com.company.actions;

import com.company.events.*;
import com.google.gson.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;

public class EventBuilder {
    private File eventsFile;
    private static JsonArray eventJsonArray;
    private static int eventTime;
    public static String eventType;
    private static Event event;

    public EventBuilder(File eventFile) {
        this.eventsFile = eventFile;
    }

    public void main(String[] args) throws IOException {
        eventsFile = new File("src/com/company/files/events3.json");
        Gson gson = new GsonBuilder().create();
        eventJsonArray = gson.fromJson(Files.newBufferedReader(new File(String.valueOf(eventsFile)).toPath(), StandardCharsets.UTF_8), JsonArray.class);
//        readJson(eventsFile);
    }

    public ArrayList<Event> readJson() throws IOException {
        Gson gson = new GsonBuilder().create();
        eventJsonArray = gson.fromJson(Files.newBufferedReader(new File(String.valueOf(eventsFile)).toPath(), StandardCharsets.UTF_8), JsonArray.class);
//        event = new Event(eventType, eventTime);

        // initiate array
        ArrayList<Event> eventsArray = new ArrayList<Event>();

        for (JsonElement jsonElement : eventJsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String eventType = jsonObject.get("type").getAsString();
            eventTime = jsonObject.get("time").getAsInt();
            JsonObject data = jsonObject.get("data").getAsJsonObject();
            int guest = data.get("guest").getAsInt();
            int stars = data.get("stars").getAsInt();
            int duration = data.get("duration").getAsInt();


            Event event = null;
            switch (eventType) {
                case "CHECK_IN":
                    event = new CheckInEvent(eventTime, guest, stars);
                case "CHECK_OUT":
                    event = new CheckOutEvent(eventTime, guest);
                    event = new CleaningEvent(eventTime, guest);
                case "GO_TO_CINEMA":
                    event = new GoToCinemaEvent(eventTime, guest);
                case "GO_TO_DINER":
                    event = new GoToDinerEvent(eventTime, guest);
                case "GO_TO_FITNESS":
                    event = new GoToFitnessEvent(eventTime, guest, duration);
                case "CLEANING_EMERGENCY":
                    event = new CleaningEmergencyEvent(eventTime, guest);
//                case "CLEANING_EVENT":
//                    event = new CleaningEvent(eventTime);
                case "GODZILLA":
                    event = new GodzillaEvent(eventTime);
                    //todo set alles wat je wilt setten
                case "EVACUATE":
                    event = new EvacuateEvent(eventTime);
                default:
                    System.out.println("No event");
            }

            if (event != null) {
                eventsArray.add(event);
            }

//
//            event = new Event(eventType, eventTime);
//
//            JsonObject data = jsonObject.get("data").getAsJsonObject();
//            if (data.has("guest")) {
//                event.setGuest(data.get("guest").getAsInt());
//            }
//            if (data.has("stars")) {
//                event.setStars(data.get("stars").getAsInt());
//            }
//            if (data.has("duration")) {
//                event.setDuration(data.get("duration").getAsInt());
//            }
//            System.out.println("Type: "+ event.getEventType()+" | Time: "+ event.getEventTime()+" | Guest: "+event.getGuest()+" | Stars: "+event.getStars()+" | Duration: "+event.getDuration());
        }

        // Sort array
        eventsArray.sort(new SortEventsByTime());

        return eventsArray;
    }

    static class SortEventsByTime implements Comparator<Event> {
        public int compare(Event a, Event b) {
            return a.getEventTime() - b.getEventTime();
        }
    }
}


