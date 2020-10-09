package com.company.models;

import com.company.actions.Dijkstra;
import com.company.actions.HotelBuilder;
import com.company.models.areas.Area;
import com.company.models.areas.GuestRoom;
import javafx.application.Platform;
import javafx.scene.layout.HBox;

import java.util.LinkedList;


public class Guest extends Person {
    private int preferredStars;
    private GuestRoom guestRoom;
    private int guestNumber;
    private boolean shown = true;

    public void setGuestImage(){
        super.setPerson("guest.jpg");
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

    public void setShown(boolean shown){
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

    }
}

