package com.company;

import java.util.ArrayList;

public class Stairs extends Area {

    private ArrayList<Person> stairsPersons;

    public Stairs (Position position, Dimensions dimensions) {
        super(position, dimensions);
        stairsPersons = new ArrayList<Person>();

    }

    public void addPerson(Person person) {
        stairsPersons.add(person);
    }
}
