package com.company.models;

import com.company.actions.Event;
import com.company.actions.Move;

public class Guest extends Person implements EventListener {
    private int preferredStars;
    Event event;

    public Guest() {
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

    public void handleEvent() {
        switch (event.getEventType()) {

            case "CHECK_IN":
            case "CHECK_OUT":
            case "EVACUATE":
            case "GO_TO_CINEMA":
            case "GO_TO_DINER":
            case "GO_TO_FITNESS":
            case "GODZILLA":

            default:
                System.out.println("No event");
        }

    }

    public void checkIn(Event event){

    }

    public void checkOut(Event event){

    }

    public void evacuate(Event event){

    }

    public void goToCinema(Event event){

    }

    public void goToDiner(Event event){

    }
    public void goToFitness(Event event){

    }

    public void godzilla(Event event) {

    }


}
