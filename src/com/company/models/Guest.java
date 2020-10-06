package com.company.models;

import com.company.actions.Event;
import com.company.actions.Move;

public class Guest extends Person implements EventListener {
    private int preferredStars;
    Event event;
    int idGuest;

    public int getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(int idGuest) {
        this.idGuest = idGuest;
    }


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

    public void handleEvent(Event event) {
        Guest guest = null;

//        switch (event.getEventType()) {
//
//            case "CHECK_IN":
//                guest = new Guest();
//                guest.setPreferredStars(event.getStars());
//                guest.setIdGuest(event.getGuest());
//                Hotel.guestsArrayList.add(guest);
//                //TODO add guest to AREA array
//                System.out.println("guest" + idGuest + "stars: "+ preferredStars);
//            case "CHECK_OUT":
//                if (event.getGuest() == guest.getIdGuest()) {
//                    Hotel.guestsArrayList.remove(guest);
//                }
//            case "EVACUATE":
//                if (event.getGuest() == guest.getIdGuest()) {
//                    Hotel.guestsArrayList.remove(guest);
//                }
//            case "GO_TO_CINEMA":
//                if (event.getGuest() == guest.getIdGuest()) {
//                    //TODO HAAL AREA OP VAN AREA array --> dijkstra
//                    //ds.findPath(areas[x][Y],areas[x][y](closest cinema))
//                    //set area van deze guest op de closest cinema
//                }
//
//            case "GO_TO_DINER":
//                if (event.getGuest() == guest.getIdGuest())    {
//                        //TODO HAAL AREA OP VAN AREA array --> dijkstra
//                        //ds.findPath(areas[x][Y],areas[x][y](closest diner))
//                        //set area van deze guest op de closest diner
//                    }
//
//            case "GO_TO_FITNESS":
//                if (event.getGuest() == guest.getIdGuest())  {
//                        //TODO HAAL AREA OP VAN AREA array --> dijkstra
//                        //ds.findPath(areas[x][Y],areas[x][y](closest fitness))
//                        //set area van deze guest op de closest fitness
//                    }
//
//            case "GODZILLA":
//                if (event.getGuest() == guest.getIdGuest()) {
//                    //TODO nog iets bedenken....
//                }
//
//
//            default:
//                System.out.println("No event");
//        }
//
//    }


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
    }}
