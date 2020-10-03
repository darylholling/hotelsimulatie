package com.company.models;

import java.io.FileNotFoundException;

public class Cinema extends Area {

    public Cinema(int x, int y, int areaWidth, int areaHeight) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);
        super.setDefaultImage(this, "cinema.jpg", areaWidth);
    }
}