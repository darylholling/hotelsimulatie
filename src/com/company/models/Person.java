package com.company.models;

abstract class Person {
    private Area area;

//    public Person(Area area) {
//        this.area = area;
//    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
