package com.company.persons;

import com.company.actions.Dijkstra;
import com.company.actions.HotelBuilder;
import com.company.listeners.LateComingHTEListener;
import com.company.models.areas.Area;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;

abstract public class Person extends Pane implements MoveInterface, LateComingHTEListener {
    protected ImageView personImageFile;
    protected Area area;
    protected LinkedList<Area> movingQueue = new LinkedList<>();
    protected boolean activeListener = true;

    //get area
    public Area getArea() {
        return this.area;
    }

    //set area
    public void setArea(Area area) {
        if (this.area != null) {
            Platform.runLater(this::removePersonFromGrid);
        }

        this.area = area;
        if (HotelBuilder.gridPane != null) {
            Platform.runLater(this::addPersonToGrid);
        }
    }

    //set image of person
    public void setPersonImage(String image) {
        ImageView personImageView = null;
        try {
            personImageView = new ImageView(new Image(new FileInputStream("src/com/company/images/" + image)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        personImageView.setFitHeight(48);
        personImageView.setFitWidth(25);
        personImageView.toFront();
        this.setPersonImageFile(personImageView);
    }

    //set person image file
    public void setPersonImageFile(ImageView personImageFile) {
        this.personImageFile = personImageFile;
        getChildren().add(this.personImageFile);
        personImageFile.toFront();
    }

    //add person to gridpane
    public void addPersonToGrid() {
        HotelBuilder.gridPane.add(this, this.getArea().getX(), this.getArea().getY());
    }

    //remove person from gridpane
    public void removePersonFromGrid() {
        HotelBuilder.gridPane.getChildren().remove(this);
    }

    //get area from moving queue
    public LinkedList<Area> getMovingQueue() {
        return movingQueue;
    }

    //set area to moving queue
    public void setMovingQueue(LinkedList<Area> movingQueue) {
        this.movingQueue = movingQueue;
    }

    //determine shortest path
    public LinkedList<Area> determineShortestPath(Area destination)
    {
        return new Dijkstra().findPath(this, destination);
    }

    //checks if it's an active listener
    public boolean isActiveListener() {
        return activeListener;
    }

    //set active listener
    public void setActiveListener(boolean activeListener) {
        this.activeListener = activeListener;
    }
}