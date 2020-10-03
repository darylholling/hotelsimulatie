package com.company.models;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Area extends Pane {
    private ImageView imageFile;
    private int x;
    private int y;
    private int areaWidth;
    private int areaHeight;
    private ArrayList<Person> persons = new ArrayList<>();

    //    The nodes neighbours with the distance to each one
    private HashMap<Area, Integer> neighbours;

    //     Data for pathfinder, keeps the current distance
    private int distance;

    //    Remembers the previous node
    private Area latest;

    public Area(int x, int y, int areaWidth, int areaHeight) {
        this.x = x;
        this.y = y;
        this.areaWidth = areaWidth;
        this.areaHeight = areaHeight;


        this.neighbours = new HashMap<>();
        this.distance = Integer.MAX_VALUE;
        this.latest = null;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void addPerson(Person person) {
        if (!this.persons.contains(person)) {
            this.persons.add(person);
        }
    }

    public void removePerson(Person person) {
        this.persons.remove(person);
    }

    public ImageView getImageFile() {
        return imageFile;
    }

    public void setImageFile(ImageView imageFile) {
        this.imageFile = imageFile;
        getChildren().add(this.imageFile);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAreaWidth() {
        return areaWidth;
    }

    public void setAreaWidth(int areaWidth) {
        this.areaWidth = areaWidth;
    }

    public int getAreaHeight() {
        return areaHeight;
    }

    public void setAreaHeight(int areaHeight) {
        this.areaHeight = areaHeight;
    }

    public HashMap<Area, Integer> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Area area, Integer distance) {
        this.neighbours.put(area, distance);
    }

    public void setNeighbours(HashMap<Area, Integer> neighbours) {
        this.neighbours = neighbours;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Area getLatest() {
        return latest;
    }

    public void setLatest(Area latest) {
        this.latest = latest;
    }
}
