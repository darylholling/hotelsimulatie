package com.company.models;

import com.company.events.Event;
import com.company.actions.Move;
import com.company.models.areas.Area;
import com.company.models.areas.GuestRoom;

public class Guest extends Person {
    private int preferredStars;
    private GuestRoom guestRoom;
    private int id;
    private boolean visible = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPreferredStars() {
        return preferredStars;
    }

    public void setPreferredStars(int preferredStars) {
        this.preferredStars = preferredStars;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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
