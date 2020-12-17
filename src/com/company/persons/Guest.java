package com.company.persons;

import com.company.models.Hotel;
import com.company.models.areas.Area;
import com.company.models.areas.GuestRoom;
import com.company.models.areas.Lobby;
import javafx.application.Platform;

import java.util.LinkedList;
import java.util.Random;

public class Guest extends Person {
    private GuestRoom guestRoom;
    private int guestNumber;
    private int checkInTime;
    private boolean movingToCheckout;

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

    public String randomSelect() {
        String[] arr = {"guest.png", "guest2.png"};
        Random random = new Random();

        return arr[random.nextInt(arr.length)];
    }

    @Override
    public void move(Area startArea, Area endArea) {
        this.changeArea(startArea, endArea);

        if (this.movingQueue.size() == 1) {
            this.movingQueue.remove(endArea);

            if (endArea instanceof Lobby && this.isMovingToCheckout()) {
                Hotel hotel = this.getArea().getHotel();
                this.setActiveListener(false);
                hotel.removeGuestFromActiveList(this);
            }
        }
    }

    @Override
    public void updatedHTE(int HTE) {
        if (movingQueue.size() == 1) {
            this.movingQueue.addFirst(this.getArea());
        }

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

    public boolean isMovingToCheckout() {
        return movingToCheckout;
    }

    public void setMovingToCheckout(boolean movingToCheckout) {
        this.movingToCheckout = movingToCheckout;
    }
}
