package com.company.persons;

import com.company.models.Hotel;
import com.company.models.areas.*;
import javafx.application.Platform;

import java.util.LinkedList;
import java.util.Random;

public class Guest extends Person {
    private GuestRoom guestRoom;
    private int guestNumber;
    private int checkInTime;

    public void setGuestImage() {
        super.setPersonImage(randomSelect());
    }

    public void setCheckInTime(int checkInTime) {
        this.checkInTime = checkInTime;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public void setGuestNumber(int guestNumber) {
        this.guestNumber = guestNumber;
    }

    public GuestRoom getGuestRoom() {
        return guestRoom;
    }

    public void setGuestRoom(GuestRoom guestRoom) {
        this.guestRoom = guestRoom;
    }

    // selects random picture
    public String randomSelect() {
        String[] arr = {"guest.png", "guest2.png"};
        Random random = new Random();
        int select = random.nextInt(arr.length);
        return arr[select];
    }

    @Override
    public void move(Area startArea, Area endArea) {
        this.getArea().removePerson(this);
        this.setArea(endArea);
        endArea.addPerson(this);
        this.movingQueue.remove(startArea);

        if (this.movingQueue.size() == 1) {
            this.movingQueue.remove(endArea);

            if (endArea instanceof Lobby) {
                Hotel hotel = this.getArea().getHotel();
                hotel.removeGuestFromActiveList(this);
                this.setActiveListener(false);
            }
        }
    }

    @Override
    public void updatedHTE(int HTE) {
        if (this.movingQueue.size() > 1 && HTE != checkInTime) {
            this.move(this.movingQueue.getFirst(), this.movingQueue.get(1));

            if (this.movingQueue.isEmpty()) {
                Platform.runLater(this::removePersonFromGrid);
            }
        }
    }

    public void addShortestPathToMovingQueueByAreaType(String areaType) {
        Area[] arealist = this.guestRoom.getHotel().getAreasForType(areaType);

        if (arealist != null) {
            LinkedList<Area> selectedPath = this.determinePathByAreaList(arealist);

            if (selectedPath != null && !selectedPath.isEmpty()) {
                this.setMovingQueue(selectedPath);
            }
        }
    }

    private LinkedList<Area> determinePathByAreaList(Area[] areas) {
        LinkedList<Area> selectedPath = null;
        int closestDistance = Integer.MAX_VALUE;

        for (Area area : areas) {
            LinkedList<Area> currentPath = this.determineShortestPath(area);

            int distance = currentPath.size();

            if (closestDistance > distance) {
                closestDistance = distance;
                selectedPath = currentPath;
            }
        }

        return selectedPath;
    }
}
