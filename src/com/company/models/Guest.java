package com.company.models;

import com.company.actions.Event;
import com.company.actions.Move;

public class Guest extends Person {
    private int preferredStars;

    public Guest () {
        super();
    }

    public void person(Area area, Event events, Move move) {

    }

    public int getPreferredStars() {
        return preferredStars;
    }

    public void setPreferredStars(int preferredStars) {
        this.preferredStars = preferredStars;
    }

    @Override
    public void execute(Event event) {

    }
}
