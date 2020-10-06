package com.company.actions;

import com.company.models.HTEListener;
import com.company.models.Settings;
import com.company.models.StartListener;

import java.io.IOException;
import java.util.Queue;

public class EventHandler implements StartListener, HTEListener {
    private Settings settings;
    private Queue<Event> eventQueue;

    public EventHandler(Settings settings) {
        this.settings = settings;
    }

    public void startEvents() throws IOException {
        EventBuilder eventBuilder = new EventBuilder(settings.getEventsFile());
        this.eventQueue = eventBuilder.readJson();
    }

    public void handleEvents(int HTE) {
        Event[] events = eventQueue.stream().filter(event -> event.getEventTime() == HTE).toArray(Event[]::new);

        for (Event event : events) {
            // todo insert mirjams code here
        }
    }

    @Override
    public void handleStart() throws Exception {
        this.startEvents();
    }

    @Override
    public void updatedHTE(int HTE) {
        this.handleEvents(HTE);
    }
}
