package com.company.actions;

import com.company.events.Event;
import com.company.listeners.HTEListener;
import com.company.models.Hotel;
import com.company.listeners.StartListener;

import java.util.Queue;

public class EventHandler implements StartListener, HTEListener {
    private Queue<Event> eventQueue;
    private Hotel hotel;

    public EventHandler(Hotel hotel) {
        this.hotel = hotel;
    }

    public Queue<Event> getEventsQueue() {
        return eventQueue;
    }

    @Override
    public void handleStart() {
        this.initializeEvents();
    }

    private void initializeEvents() {
        EventBuilder eventBuilder = new EventBuilder();
        Queue<Event> eventQueue = eventBuilder.readJson(hotel);

        if (eventQueue != null) {
            this.eventQueue = eventQueue;
        }
    }

    @Override
    public void updatedHTE(int HTE) {
        if (this.eventQueue != null) {
            this.handleEvents(HTE);
        }
    }

    private void handleEvents(int HTE) {
        Event[] events = eventQueue.stream().filter(event -> event.getEventTime() == HTE).toArray(Event[]::new);

        for (Event event : events) {
            event.fire();
        }
    }
}