
package com.company;

public class Diner extends Area {

    private int capacity;

    public Diner (Position position, Dimensions dimensions, int capacity) {
        super(position, dimensions);

        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
