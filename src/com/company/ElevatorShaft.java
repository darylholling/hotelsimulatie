package com.company;

public class ElevatorShaft extends Area {

    private Elevator elevator;

    public ElevatorShaft(Position position, Dimensions dimensions, Elevator elevator) {
        super(position, dimensions);

        this.elevator = elevator;
    }
}