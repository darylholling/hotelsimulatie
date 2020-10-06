package com.company.actions;

import com.company.events.Event;
import com.company.models.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;

public class EventHandler implements StartListener, HTEListener {
    private Settings settings;
    private Queue<Event> eventQueue;
    private Hotel hotel;

    public EventHandler(Settings settings, Hotel hotel) {
        this.settings = settings;
        this.hotel = hotel;
    }

    private void initializeEvents() throws IOException {
        EventBuilder eventBuilder = new EventBuilder(settings.getEventsFile());
        this.eventQueue = eventBuilder.readJson(this.hotel);
    }

    private void handleEvents(int HTE) {
        Event[] events = eventQueue.stream().filter(event -> event.getEventTime() == HTE).toArray(Event[]::new);

        for (Event event : events) {
            event.fire();
        }
    }

    @Override
    public void handleStart() throws Exception {
        this.initializeEvents();
    }

    @Override
    public void updatedHTE(int HTE) {
        this.handleEvents(HTE);
    }
}
