package com.company.models;

import java.io.FileNotFoundException;

public class ElevatorShaft extends Area {
    private Elevator elevator;

    public ElevatorShaft(int x, int y, int areaWidth, int areaHeight) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);
        super.setDefaultImage(this, "elevatorShaftClosed.jpg", areaWidth);
    }
}