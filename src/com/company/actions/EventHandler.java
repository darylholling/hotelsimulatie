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

    @Override
    //handles the notification received from StartListener
    public void handleStart() {
        this.initializeEvents();
    }

    //initialize events add them to eventQueue
    private void initializeEvents() {
        EventBuilder eventBuilder = new EventBuilder();
        Queue<Event> eventQueue = eventBuilder.readJson(hotel);

        if (eventQueue != null) {
            this.eventQueue = eventQueue;
        }
    }


    @Override
    //updates the HTE
    public void updatedHTE(int HTE) {
        if (this.eventQueue != null) {
            this.handleEvents(HTE);
        }
    }

    //send trigger to the fireableListener
    private void handleEvents(int HTE) {
        Event[] events = eventQueue.stream().filter(event -> event.getEventTime() == HTE).toArray(Event[]::new);

        for (Event event : events) {
            event.fire();
        }
    }
}