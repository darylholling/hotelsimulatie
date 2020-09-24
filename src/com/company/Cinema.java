package com.company;


import java.io.File;
import java.util.ArrayList;

public class Cinema extends Area {
    private ArrayList<Person> cinemaGuests;

    public Cinema (Position position, Dimensions dimensions) {
        super(position, dimensions);

        cinemaGuests = new ArrayList<Person>();

    }

    public void addGuest(Person person) {
        cinemaGuests.add(person);
    }

}


