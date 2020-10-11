package com.company.models.areas;

import java.io.FileNotFoundException;

public class ElevatorShaft extends Area {
    public ElevatorShaft(int x, int y, int areaWidth, int areaHeight) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);
        super.setDefaultImage(this, "elevatorShaftClosed.jpg", areaWidth);
    }
}