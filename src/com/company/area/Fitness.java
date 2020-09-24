package com.company.area;

import com.company.person.Person;
import com.company.hotel.Position;

import java.io.File;
import java.util.ArrayList;

public class Fitness extends Area {

    private ArrayList<Person> fitnessGuests;

    public Fitness (Position position, Person persons, File imageFile, Dimensions dimensions, String areaType) {
        super(position, persons, imageFile, dimensions, areaType);

    }

}
