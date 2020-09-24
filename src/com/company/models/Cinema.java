package com.company.models;


import java.io.File;
import java.util.ArrayList;

public class Cinema extends Area {
    private ArrayList<Person> cinemaGuests;

    public Cinema (Position position, Person persons, File imageFile, Dimensions dimensions, String areaType) {
        super(position, persons, imageFile, dimensions, areaType);
        cinemaGuests = new ArrayList<Person>();

    }

    public void addGuest(Person person) {
        cinemaGuests.add(person);
    }

}


