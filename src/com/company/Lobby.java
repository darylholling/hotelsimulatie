package com.company;

import java.io.File;
import java.util.ArrayList;

public class Lobby extends Area {

    private ArrayList<Person> lobbyPersons;

    public Lobby (Position position, Person persons, File imageFile, Dimensions dimensions, String areaType) {
        super(position, persons, imageFile, dimensions, areaType);
        lobbyPersons = new ArrayList<Person>();

    }

    public void addPerson(Person person) {
        lobbyPersons.add(person);
    }
}
