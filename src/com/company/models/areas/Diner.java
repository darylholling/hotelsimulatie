package com.company.models.areas;

import java.io.FileNotFoundException;

public class Diner extends Area {
    private int capacity;

    public Diner(int x, int y, int areaWidth, int areaHeight, int capacity) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);
        super.setDefaultImage(this, "diner.jpg", areaWidth);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
