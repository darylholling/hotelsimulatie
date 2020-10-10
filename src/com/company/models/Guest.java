package com.company.models;

import com.company.actions.Dijkstra;
import com.company.actions.HotelBuilder;
import com.company.models.areas.Area;
import com.company.models.areas.GuestRoom;
import javafx.application.Platform;
import javafx.scene.layout.HBox;

import java.util.LinkedList;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;

public class Guest extends Person {
    private int preferredStars;
    private GuestRoom guestRoom;
    private int guestNumber;
    private boolean shown = true;
    private boolean movingToCheckOut = false;

    public boolean isMovingToCheckOut() {
        return movingToCheckOut;
    }

    public void setMovingToCheckOut(boolean movingToCheckOut) {
        this.movingToCheckOut = movingToCheckOut;
    }
    private int checkInTIme;

    public void setGuestImage() {
        super.setPersonImage("guest.png");
    }

    public int getCheckInTIme() {
        return checkInTIme;
    }

    public void setCheckInTIme(int checkInTIme) {
        this.checkInTIme = checkInTIme;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public void setGuestNumber(int guestNumber) {
        this.guestNumber = guestNumber;
    }

    public int getPreferredStars() {
        return preferredStars;
    }

    public void setPreferredStars(int preferredStars) {
        this.preferredStars = preferredStars;
    }

    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    public GuestRoom getGuestRoom() {
        return guestRoom;
    }

    public void setGuestRoom(GuestRoom guestRoom) {
        this.guestRoom = guestRoom;
    }


    @Override
    public void move(Area startArea, Area endArea) {
        //TODO zorg dat het plaatje niet zichtbaar is
//        this.removeGuestImage();
//        Platform.runLater(()->this.removePersonImageFile("guest.png"));

        System.out.println("old location" + startArea.getX() + ":" + startArea.getY());
        this.getArea().removePerson(this);
        this.setArea(endArea);
        endArea.addPerson(this);
        this.movingQueue.remove(endArea);
        //TODO zorg dat het plaatje wel zichtbaar is
        this.shown = true;
        System.out.println("New location" + endArea.getX() + ":" + endArea.getY());

    }

    @Override
    public void updatedHTE(int HTE) {
        if (movingQueue.size() != 0 && HTE != checkInTIme) {
            this.move(this.getArea(), this.movingQueue.getFirst());

            if (this.movingQueue.size() == 0 ) {
                Platform.runLater(() -> this.removePersonFromGrid());
                if (movingToCheckOut) {
                }
            }
        }
    }
}
