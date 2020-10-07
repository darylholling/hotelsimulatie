package com.company.actions;

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
    private static JsonArray eventJsonArray;
    private static int eventTime;
    public static int highestHteInJsonFile;
    private static String eventType;
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

    public Queue<Event> readJson() throws IOException {
        eventsFile = new File("src/com/company/files/events3.json");
        Gson gson = new GsonBuilder().create();
        eventJsonArray = gson.fromJson(Files.newBufferedReader(new File(String.valueOf(eventsFile)).toPath(), StandardCharsets.UTF_8), JsonArray.class);
        event = new Event(eventType, eventTime);

        // initiate array
        ArrayList<Event> eventsArray = new ArrayList<Event>();

        for (JsonElement jsonElement : eventJsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            eventTime = jsonObject.get("time").getAsInt();
            eventType = jsonObject.get("type").getAsString();
            event = new Event(eventType, eventTime);

            eventsArray.add(event);
        }

        // Sort array
        eventsArray.sort(new SortEventsByTime());

        // Determine the highest HTE
        highestHteInJsonFile = eventsArray.stream().reduce((first, second) -> second).orElse(null).getEventTime();

        // Create queue
        return new PriorityQueue<>(eventsArray);
    }

    static class SortEventsByTime implements Comparator<Event> {
        public int compare(Event a, Event b) {
            return a.getEventTime() - b.getEventTime();
        }
    }

}


