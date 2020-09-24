package com.company.models;

import java.io.File;
import java.util.ArrayList;

public class Lobby extends Area {

    private ArrayList<Person> lobbyPersons;

    public Lobby (Position position, Dimensions dimensions) {
        super(position, dimensions);
        lobbyPersons = new ArrayList<Person>();

    }

    public void addPerson(Person person) {
        lobbyPersons.add(person);
    }
}
