package com.company.persons;

import com.company.events.CleaningEmergencyEvent;
import com.company.events.CleaningEvent;
import com.company.events.DefaultCleaningEvent;
import com.company.listeners.CleaningListener;
import com.company.models.Hotel;
import com.company.models.Settings;
import com.company.models.areas.Area;
import com.company.models.areas.GuestRoom;
import com.company.models.areas.Lobby;
import javafx.application.Platform;

public class Cleaner extends Person implements CleaningListener {
    private Hotel hotel;
    private boolean cleaning = false;
    private CleaningEvent currentCleanEvent;
    private int endHTE;

    public Cleaner(Hotel hotel, Area area) {
        super.setArea(area);
        this.hotel = hotel;
        this.setCleanerImage();
    }

    //set image of cleaner
    public void setCleanerImage() {
        super.setPersonImage("cleaner.png");
    }

    //check queue for cleaning events
    private void checkQueue() {

        //checks which cleaning event is available
        if (currentCleanEvent != null) {
            if (currentCleanEvent instanceof CleaningEmergencyEvent) {
                moveToCleaning(currentCleanEvent);
                return;
            }
            if (currentCleanEvent instanceof DefaultCleaningEvent) {
                if (!hotel.cleaningEmergencyEvents.isEmpty()) {
                    hotel.defaultCleaningEvents.add((DefaultCleaningEvent) currentCleanEvent);
                    currentCleanEvent = hotel.cleaningEmergencyEvents.poll();
                    moveToCleaning(currentCleanEvent);
                    return;
                }
                moveToCleaning(currentCleanEvent);
            }
            return;
        }

        if (!hotel.cleaningEmergencyEvents.isEmpty()) {
            currentCleanEvent = hotel.cleaningEmergencyEvents.poll();
            moveToCleaning(currentCleanEvent);
            return;
        }

        if (!hotel.defaultCleaningEvents.isEmpty()) {
            currentCleanEvent = hotel.defaultCleaningEvents.poll();
            moveToCleaning(currentCleanEvent);
            return;
        }

        if (!(this.getArea() instanceof Lobby) && currentCleanEvent == null) {
            this.setMovingQueue(this.determineShortestPath(hotel.getLobby()));
        }
    }

    //sends the cleaner to the cleaning event
    private void moveToCleaning(CleaningEvent event) {
        this.setMovingQueue(this.determineShortestPath(hotel.getGuestByNumber(event.guestNumber).getGuestRoom()));
    }

    //cleaner starts cleaning
    public void cleaning() {
        this.endHTE = hotel.currentHTE + Settings.getSettings().getCleanHTE();
        cleaning = true;
    }

    @Override

    //sends the cleaner to the guestroom
    public void move(Area startArea, Area endArea) {
        this.getArea().removePerson(this);
        this.setArea(endArea);
        endArea.addPerson(this);
        this.movingQueue.remove(startArea);
        if (this.movingQueue.size() == 1 && this.movingQueue.getFirst() == endArea) {
            this.movingQueue.remove(endArea);
            cleaning();

            if (endArea instanceof GuestRoom) {
                ((GuestRoom) endArea).setClean(true);
            }
        }
    }

    @Override
    //actions
    public void updatedHTE(int HTE) {
        if (movingQueue.size() == 1) {
            this.movingQueue.addFirst(this.getArea());
        }
        if (movingQueue.size() > 1) {
            this.move(this.movingQueue.getFirst(), this.movingQueue.get(1));
        }
        if (this.movingQueue.isEmpty() && !(this.getArea() instanceof Lobby)) {
            Platform.runLater(this::removePersonFromGrid);
        }
        if (hotel.getCurrentHTE() == this.endHTE) {
            currentCleanEvent = null;
            cleaning = false;
            checkQueue();
        }
    }

    //start cleaners
    public void startCleaners() {
        if (!cleaning) {
            checkQueue();
        }
    }
}
