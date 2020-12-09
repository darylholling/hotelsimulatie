package com.company.models.areas;

import com.company.models.Hotel;
import com.company.persons.Person;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Area extends Pane {
    protected ImageView imageFile;
    protected int x;
    protected int y;
    protected int areaWidth;
    protected int areaHeight;
    protected ArrayList<Person> persons = new ArrayList<>();
    protected Hotel hotel;
    // Remember distance per person
    protected HashMap<Person, Integer> distancesPerPerson = new HashMap<>();
    // Remember latest per person
    protected HashMap<Person, Area> latestPerPerson = new HashMap<>();
    //    The nodes neighbours with the distance to each one
    protected HashMap<Area, Integer> neighbours;

    public Area(int x, int y, int areaWidth, int areaHeight) {
        this.x = x;
        this.y = y;
        this.areaWidth = areaWidth;
        this.areaHeight = areaHeight;
        this.neighbours = new HashMap<>();
    }

//    add Person
    public void addPerson(Person person) {
        if (!this.persons.contains(person)) {
            this.persons.add(person);
        }
    }

//    remove Person
    public void removePerson(Person person) {
        this.persons.remove(person);
            }

//    get X
    public int getX() {
        return x;
    }

//    set X
    public void setX(int x) {
        this.x = x;
    }

//    get Y
    public int getY() {
        return y;
    }

//    set Y
    public void setY(int y) {
        this.y = y;
    }

//    get area width
    public int getAreaWidth() {
        return areaWidth;
    }

//    get area height
    public int getAreaHeight() {
        return areaHeight;
    }

//    get neighbours
    public HashMap<Area, Integer> getNeighbours() {
        return neighbours;
    }

//    add neighbour with distance to each one
    public void addNeighbour(Area area, Integer distance) {
        this.neighbours.put(area, distance);
    }

//    get hotel
    public Hotel getHotel() {
        return hotel;
    }

//    set hotel
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

//    creates the area background
    public AreaBackground createAreaBackground(int defaultX, int defaultY, int width, int height, ImageView classname) {
        return new AreaBackground(defaultX, defaultY, width, height, classname);
    }

//    set the image based on string image
    public void setDefaultImage(Area area, String image, int areaWidth) throws FileNotFoundException {
        ImageView imageView = new ImageView(new Image(new FileInputStream("src/com/company/images/" + image)));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50 * areaWidth);
        area.setImageFile(imageView);
    }

//      get ImageFile
    public ImageView getImageFile() {
        return imageFile;
    }
//    set ImageFile
    public void setImageFile(ImageView imageFile) {
        this.imageFile = imageFile;
        getChildren().add(this.imageFile);
    }

//    get distance for person
    public int getDistanceForPerson(Person person) {
        if (this.distancesPerPerson.containsKey(person)) {
            return this.distancesPerPerson.get(person);
        }
        return Integer.MAX_VALUE;
    }

//    set distance for person
    public void setDistanceForPerson(Person person, int distance) {
        this.distancesPerPerson.put(person, distance);
    }

//    get latest area for person
    public Area getLatestForPerson(Person person) {
        if (this.distancesPerPerson.containsKey(person)) {
            return this.latestPerPerson.get(person);
        }
        return null;
    }

//    set latest area for person
    public void setLatestForPerson(Person person, Area latest) {
        this.latestPerPerson.put(person, latest);
    }
}
