package com.company.actions;

import com.company.events.Event;
import com.company.models.*;

import java.io.IOException;
import java.util.Queue;

public class EventHandler implements StartListener, HTEListener {
    private Queue<Event> eventQueue;
    private Hotel hotel;

    public EventHandler(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void handleStart() throws Exception {
        this.initializeEvents();
    }

    private void initializeEvents() throws IOException {
        EventBuilder eventBuilder = new EventBuilder(hotel.settings.getEventsFile());
        this.eventQueue = eventBuilder.readJson(this.hotel);
    }

    @Override
    public void updatedHTE(int HTE) {
        this.handleEvents(HTE);
    }

    private void handleEvents(int HTE) {
        Event[] events = eventQueue.stream().filter(event -> event.getEventTime() == HTE).toArray(Event[]::new);

        for (Event event : events) {
            event.fire();
        }
    }
}
