package com.company.models;

import com.company.actions.Event;
import com.company.actions.Move;

public class Guest extends Person implements EventListener {
    private int preferredStars;
    Event event;

    public int getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(int idGuest) {
        this.idGuest = idGuest;
    }

    int idGuest;


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
        Guest guest;

        switch (event.getEventType()) {

            case "CHECK_IN":
                guest = new Guest();
                guest.setPreferredStars(event.getStars());
                guest.setIdGuest(event.getGuest());
                System.out.println("guest" + idGuest);
            case "CHECK_OUT":
//                if (guest != null) {
//                    guest.getIdGuest(event.getGuest());
//
//
//                }
            case "EVACUATE":
            case "GO_TO_CINEMA":
            case "GO_TO_DINER":
            case "GO_TO_FITNESS":
            case "GODZILLA":

            default:
                System.out.println("No event");
        }

    }


//    public void checkIn(Event eventType, Event eventTime, Event guestNumber, Event stars) {
//    }
//
//    public void checkOut(Event event) {
//    }
//
//    public void evacuate(Event event) {
//    }
//
//    public void goToCinema(Event event) {
//    }
//
//    public void goToDiner(Event event) {
//    }
//
//    public void goToFitness(Event event) {
//    }
//
//    public void godzilla(Event event) {
//    }
}
