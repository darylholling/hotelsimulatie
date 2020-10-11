package com.company.models;

import com.company.models.areas.Area;
import com.company.models.areas.GuestRoom;
import javafx.application.Platform;

import java.util.LinkedList;
import java.util.Random;

public class Guest extends Person {
    private int preferredStars;
    private GuestRoom guestRoom;
    private int guestNumber;
    private boolean movingToCheckOut = false;
    private int checkInTime;

    public boolean isMovingToCheckOut() {
        return movingToCheckOut;
    }

    public void setMovingToCheckOut(boolean movingToCheckOut) {
        this.movingToCheckOut = movingToCheckOut;
    }

    public void setGuestImage() {
        super.setPersonImage(randomSelect());
    }

    public int getCheckInTime() {
        return checkInTime;
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

    public void setPreferredStars(int preferredStars) {
        this.preferredStars = preferredStars;
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
        if (this.movingQueue.size() == 1 && this.movingQueue.getFirst() == endArea){
            this.movingQueue = new LinkedList<>();
        }

    }

    @Override
    public void updatedHTE(int HTE) {
        if (this.movingQueue.size() > 1 && HTE != checkInTime) {
            //System.out.println(this.movingQueue);
            //System.out.println("guest" + guestNumber);
            if (this.movingQueue.size() > 1) {
                this.move(this.movingQueue.getFirst(), this.movingQueue.get(1));
            }

            if (this.movingQueue.isEmpty()) {
                Platform.runLater(() -> this.removePersonFromGrid());
            }
        }
    }
}
