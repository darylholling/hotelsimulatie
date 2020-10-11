package com.company.models.areas;

import com.company.models.Hotel;
import com.company.models.Person;
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

    //    The nodes neighbours with the distance to each one
    private HashMap<Area, Integer> neighbours;

    //     Data for pathfinder, keeps the current distance
    private int distance;

    //    Remembers the previous node
    private Area latest;

    // Remember distance per person
    protected HashMap<Person, Integer> distancesPerPerson = new HashMap<>();

    // Remember latest per person
    protected HashMap<Person, Area> latestPerPerson = new HashMap<>();

    // [ {guestNumber} => 'Guest'

    public Area(int x, int y, int areaWidth, int areaHeight) {
        this.x = x;
        this.y = y;
        this.areaWidth = areaWidth;
        this.areaHeight = areaHeight;

        this.neighbours = new HashMap<>();
        this.distance = Integer.MAX_VALUE;
        this.latest = null;
    }

    public void addPerson(Person person) {
        if (!this.persons.contains(person)) {
            this.persons.add(person);
        }
    }

    public void removePerson(Person person) {
        this.persons.remove(person);
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

    public int getAreaHeight() {
        return areaHeight;
    }

    public HashMap<Area, Integer> getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Area area, Integer distance) {
        this.neighbours.put(area, distance);
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public AreaBackground createAreaBackground(int defaultX, int defaultY, int width, int height, ImageView classname) throws FileNotFoundException {
        return new AreaBackground(defaultX, defaultY, width, height, classname);
    }

    public void setDefaultImage(Area area, String image, int areaWidth) throws FileNotFoundException {
        ImageView imageView = new ImageView(new Image(new FileInputStream("src/com/company/images/" + image)));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50 * areaWidth);
        area.setImageFile(imageView);
    }

    public ImageView getImageFile() {
        return imageFile;
    }

    public void setImageFile(ImageView imageFile) {
        this.imageFile = imageFile;
        getChildren().add(this.imageFile);
    }

    public String printCoordinates() {
        return "x:" + this.x + "-y:" + y;
    }

    public void resetForPerson(Person person) {
        this.distancesPerPerson = new HashMap<>();
    }

    public int getDistanceForPerson(Person person) {
        if(this.distancesPerPerson.containsKey(person)) {
            return this.distancesPerPerson.get(person);
        }
        return Integer.MAX_VALUE;
}

    public void setDistanceForPerson(Person person, int distance) {
        this.distancesPerPerson.put(person, distance);
    }

    public Area getLatestForPerson(Person person) {
        if(this.distancesPerPerson.containsKey(person)) {
            return this.latestPerPerson.get(person);
        }
        return null;
    }

    public void setLatestForPerson(Person person, Area latest) {
        this.latestPerPerson.put(person, latest);
    }

}
