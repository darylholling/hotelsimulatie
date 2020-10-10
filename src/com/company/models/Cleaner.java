package com.company.models;

import com.company.actions.Dijkstra;
import com.company.events.CleaningEmergencyEvent;
import com.company.events.CleaningEvent;
import com.company.events.DefaultCleaningEvent;
import com.company.events.Event;
import com.company.models.areas.Area;
import com.company.models.areas.Lobby;
import javafx.application.Platform;

import java.util.LinkedList;
import java.util.Queue;

public class Cleaner extends Person implements EventListener, CleaningListener {
    private final Hotel hotel;
    private boolean cleaning = false;
    private CleaningEvent currentCleanEvent;
    private int endHTE;

    public Cleaner(Hotel hotel, Area area) {
        super.setArea(area);
        this.hotel = hotel;
    }

    public void setCleanerImage() {
        super.setPersonImage("cleaner.png");
    }

    private void checkQueue() {
        if (currentCleanEvent != null) {
            if (currentCleanEvent instanceof CleaningEmergencyEvent) {
                return;
            }
            if (currentCleanEvent instanceof DefaultCleaningEvent) {
                hotel.defaultCleaningEvents.add((DefaultCleaningEvent) currentCleanEvent);
            }
        }
        if (!hotel.cleaningEmergencyEvents.isEmpty()) {
            currentCleanEvent = hotel.hotel.cleaningEmergencyEvents.poll();
            moveToCleaning(currentCleanEvent);
        } else if (!hotel.defaultCleaningEvents.isEmpty()) {
            currentCleanEvent = hotel.defaultCleaningEvents.poll();
            moveToCleaning(currentCleanEvent);
        } else if (!(this.getArea() instanceof Lobby)) {
            moveToLobby();
        }
    }

    private void moveToLobby() {
        Dijkstra dijkstra = new Dijkstra();
        this.getArea().setDistance(0);
        LinkedList<Area> path = dijkstra.findPath(this.getArea(), hotel.getLobby());
        this.setMovingQueue(path);
    }

    private void moveToCleaning(CleaningEvent event) {
        Dijkstra dijkstra = new Dijkstra();
        this.getArea().setDistance(0);
        LinkedList<Area> path = dijkstra.findPath(this.getArea(), hotel.getGuestByNumber(event.getGuestNumber()).getGuestRoom());
        this.setMovingQueue(path);
    }

    public void cleaning() {
        int beginHTE = hotel.currentHTE;
        endHTE = beginHTE + hotel.settings.getCleanHTE();

        cleaning = true;
    }

    @Override
    public void handleEvent(Event event) {
    }

    @Override
    public void move(Area startArea, Area endArea) {
        this.getArea().removePerson(this);
        this.setArea(endArea);
        endArea.addPerson(this);
        this.movingQueue.remove(startArea);

        if (this.movingQueue.size() == 1 && this.movingQueue.getFirst() == endArea){
            this.movingQueue.remove(endArea);
            cleaning();
        }
    }

    @Override
    public void updatedHTE(int HTE) {
        if (!movingQueue.isEmpty()) {
            this.move(this.movingQueue.getFirst(), this.movingQueue.get(1));
            if (this.movingQueue.isEmpty() && !(this.getArea() instanceof Lobby)) {
                Platform.runLater(this::removePersonFromGrid);
            }
        }
        if (hotel.getCurrentHTE() == endHTE) {
            currentCleanEvent = null;
            cleaning = false;
            checkQueue();
        }
    }

    public void startCleaners(){
        if (!cleaning) {
            checkQueue();
        }
    }
}


