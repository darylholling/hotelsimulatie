package com.company.models;

abstract class Person implements EventListener{
    private Area area;

    public Person() {}

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
