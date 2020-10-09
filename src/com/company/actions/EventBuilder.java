package com.company.actions;

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
    private File eventsFile;
    private JsonArray eventJsonArray;
    private int eventTime;
    public static int highestHteInJsonFile;

    public EventBuilder(File eventFile) {
        this.eventsFile = eventFile;
    }

    public Queue<Event> readJson(Hotel hotel) throws IOException {
        eventsFile = new File("src/com/company/files/events3.json");
        Gson gson = new GsonBuilder().create();
        eventJsonArray = gson.fromJson(Files.newBufferedReader(new File(String.valueOf(eventsFile)).toPath(), StandardCharsets.UTF_8), JsonArray.class);

        ArrayList<Event> eventsArray = new ArrayList<>();

        for (JsonElement jsonElement : eventJsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String eventType = jsonObject.get("type").getAsString();
            eventTime = jsonObject.get("time").getAsInt();
            JsonObject data = jsonObject.get("data").getAsJsonObject();

            if (eventTime > highestHteInJsonFile){
                highestHteInJsonFile = eventTime;
//                System.out.println("Highest is  " + highestHteInJsonFile);
            }

            Guest guest = new Guest(); // @todo can we move this to the if data has guest statement?
            int guestNumber = 0;
            int stars = 0;
            int duration = 0;

            if (data.has("guest")) {
                guestNumber = data.get("guest").getAsInt();

                //Guest guest = new Guest();
                guest.setGuestNumber(guestNumber);
                guest.setPreferredStars(stars);

                guest.setArea(hotel.getLobby());
                guest.setGuestImage();
                guest.setShown(false);

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
                    event = new CheckInEvent(hotel, eventTime, guest, stars);
                    break;
                case "CHECK_OUT":
                    event = new CheckOutEvent(hotel, eventTime, guest, guestNumber);
                break;
//                case "GO_TO_CINEMA":
//                    event = new GoToCinemaEvent(eventTime, hotel, guest);
//                    break;
//                case "GO_TO_DINER":
//                    event = new GoToDinerEvent(eventTime, hotel, guest);
//                    break;
//                case "GO_TO_FITNESS":
//                    event = new GoToFitnessEvent(guestList, eventTime, guest, duration);
//                break;
//                case "CLEANING_EMERGENCY":
//                    event = new CleaningEmergencyEvent(guestList, eventTime, guest);
//                break;
////                case "CLEANING_EVENT":
////                    event = new CleaningEvent(eventTime);
//                break;
//                case "GODZILLA":
////                    event = new GodzillaEvent(guestList, eventTime);
//                break;
//                    //todo set alles wat je wilt setten
//                case "EVACUATE":
//                    event = new EvacuateEvent(guestList, eventTime);
//                break;
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


