package com.company.models.areas;

import java.io.FileNotFoundException;

public class Diner extends Area {
    public Diner(int x, int y, int areaWidth, int areaHeight) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);
        super.setDefaultImage(this, "diner.jpg", areaWidth);
    }
}
