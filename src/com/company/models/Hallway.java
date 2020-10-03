package com.company.models;

import java.io.FileNotFoundException;

public class Hallway extends Area {
    public Hallway(int x, int y, int areaWidth, int areaHeight) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);
        super.setDefaultImage(this, "hallway.jpg",areaWidth);
    }
}


