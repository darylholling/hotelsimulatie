package com.company.models;

import java.io.File;
import java.util.ArrayList;

public class Stairs extends Area {

    private ArrayList<Person> stairsPersons;

    public Stairs (Position position, Person persons, File imageFile, Dimensions dimensions, String areaType) {
        super(position, persons, imageFile, dimensions, areaType);
        stairsPersons = new ArrayList<Person>();

    }

    public void addPerson(Person person) {
        stairsPersons.add(person);
    }
}