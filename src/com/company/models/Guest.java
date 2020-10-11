package com.company.models;

import com.company.models.areas.Area;
import com.company.models.areas.GuestRoom;
import com.company.models.areas.Lobby;
import javafx.application.Platform;

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

        if (this.movingQueue.size() == 1){
            this.movingQueue.remove(endArea);
            if (endArea instanceof Lobby) {
                Hotel hotel = this.getArea().getHotel();
                hotel.activeGuestList.remove(this);
                Platform.runLater(() -> hotel.lateComingHTEListeners.remove(this));
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
}
