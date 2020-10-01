package com.company.actions;

import com.company.models.Data;
import com.company.models.EventType;

public class Event {

    private EventType type;
    private Integer time;
    private Data data;

    public Event(EventType type, Integer time, Data data) {
        this.type = type;
        this.time = time;
        this.data = data;
    }

    }





