package com.company.models;

import com.company.models.areas.Area;
import com.company.models.areas.GuestRoom;
import javafx.application.Platform;

public class Guest extends Person {
    private int preferredStars;
    private GuestRoom guestRoom;
    private int guestNumber;
    private boolean movingToCheckOut = false;
    private int checkinTime;

    public boolean isMovingToCheckOut() {
        return movingToCheckOut;
    }

    public void setMovingToCheckOut(boolean movingToCheckOut) {
        this.movingToCheckOut = movingToCheckOut;
    }

    public void setGuestImage() {
        super.setPersonImage("guest.png");
    }

    public void setCheckinTime(int checkinTime) {
        this.checkinTime = checkinTime;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public void setGuestNumber(int guestNumber) {
        this.guestNumber = guestNumber;
    }

    public void setPreferredStars(int preferredStars) {
        this.preferredStars = preferredStars;
    }

    public GuestRoom getGuestRoom() {
        return guestRoom;
    }

    public void setGuestRoom(GuestRoom guestRoom) {
        this.guestRoom = guestRoom;
    }


    @Override
    public void move(Area startArea, Area endArea) {
        this.getArea().removePerson(this);
        this.setArea(endArea);
        endArea.addPerson(this);
        this.movingQueue.remove(startArea);

        if (this.movingQueue.size() == 1 && this.movingQueue.getFirst() == endArea) {
            this.movingQueue.remove(endArea);
        }
    }

    @Override
    public void updatedHTE(int HTE) {
        if (!movingQueue.isEmpty() && HTE != checkinTime) {
            System.out.println(this.movingQueue);
            this.move(this.movingQueue.getFirst(), this.movingQueue.get(1));

            if (this.movingQueue.isEmpty()) {
                Platform.runLater(() -> this.removePersonFromGrid());
            }
        }
    }
}
