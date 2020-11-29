package com.company.models.areas;

import java.io.FileNotFoundException;

public class Diner extends Area {
    private int capacity;

    public Diner(int x, int y, int areaWidth, int areaHeight, int capacity) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);
        super.setDefaultImage(this, "diner.jpg", areaWidth);
        this.capacity = capacity;
    }

    //due to time shortage this function is not used yet.
    public boolean isFull() {
        return this.persons.size() >= this.capacity;
    }
}
