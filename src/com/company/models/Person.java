package com.company.models;

import com.company.models.areas.Area;

abstract public class Person implements MoveInterface{
    private Area area;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
