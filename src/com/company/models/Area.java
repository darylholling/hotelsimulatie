package com.company.models;

import com.company.actions.Point;
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

    public Area(int x, int y, int areaWidth, int areaHeight) {
        this.x = x;
        this.y = y;
        this.areaWidth = areaWidth;
        this.areaHeight = areaHeight;
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
}
