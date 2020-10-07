package com.company.models;

import com.company.events.Event;

    public interface EventListener {
        public void handleEvent (Event event);
}
