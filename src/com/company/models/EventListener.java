package com.company.models;

import com.company.actions.Event;

    public interface EventListener {
        public void handleEvent (Event event);
}
