package com.company;

import javafx.scene.layout.Pane;

import java.io.File;
import java.util.ArrayList;

public abstract class Area extends Pane {
    private Position position;
    private Person persons;
    private File imageFile;
    private Dimensions dimensions;
    private String areaType;

    private ArrayList<Person> personArrayList = new ArrayList<>();

    public Area(Position position, Person persons, File imageFile, Dimensions dimensions, String areaType) {
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Person getPersons() {
        return persons;
    }

    public void setPersons(Person persons) {
        this.persons = persons;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }
}
