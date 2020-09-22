package com.company;

import java.io.File;

public class GuestRoom extends Area{
    private int stars;

    public GuestRoom (Position position, Person persons, File imageFile, Dimensions dimensions, String areaType) {
        super(position, persons, imageFile, dimensions, areaType);

    }
    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
