package com.company;

import java.io.File;
import java.util.ArrayList;

public class Diner extends Area {

    private ArrayList<Person> dinerGuests;

    public Diner (Position position, Dimensions dimensions) {
        super(position, dimensions);
        dinerGuests = new ArrayList<Person>();

    }

    public void addGuest(Person person) {
        dinerGuests.add(person);
    }

}


