package com.company.actions;

import com.company.models.Settings;
import com.company.models.StartListener;

import java.io.IOException;
import java.util.ArrayList;

public class EventHandler implements StartListener {
    private Settings settings;
    private Event event;
    private ArrayList<Event> eventsArray = new ArrayList<Event>();

    public EventHandler(Settings settings) {
        this.settings = settings;
    }

    public void startEvents() throws IOException {
        EventBuilder eventBuilder = new EventBuilder(settings.getEventsFile());
        this.eventsArray = eventBuilder.readJson();
    }

    @Override
    public void handleStart() throws Exception {
        this.startEvents();
    }
}
