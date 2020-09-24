package com.company.area;

import com.company.person.Person;
import com.company.hotel.Position;

import java.io.File;
import java.util.ArrayList;

public class Diner extends Area {

    private ArrayList<Person> dinerGuests;

    public Diner (Position position, Person persons, File imageFile, Dimensions dimensions, String areaType) {
        super(position, persons, imageFile, dimensions, areaType);
        dinerGuests = new ArrayList<Person>();

    }

    public void addGuest(Person person) {
        dinerGuests.add(person);
    }

}


