package com.company.models.areas;

import java.io.FileNotFoundException;

public class Stairs extends Area {
    public Stairs(int x, int y, int areaWidth, int areaHeight) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);
        super.setDefaultImage(this, "stairs.jpg", areaWidth);
    }
}