package com.company.models;

import com.company.actions.HotelBuilder;
import java.io.FileNotFoundException;
import com.company.models.areas.Area;
import javafx.scene.image.ImageView;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import java.io.FileInputStream;

import java.util.LinkedList;

abstract public class Person extends Pane implements MoveInterface, LateComingHTEListener {
    protected ImageView personImageFile;
    protected Area area;
    protected LinkedList<Area> movingQueue = new LinkedList<>();
    private HBox imageBox;

    public Area getArea() {
        return this.area;
    }

    public void setArea(Area area) {
        if (this.area != null) {
            Platform.runLater(()->this.removePersonFromGrid());
        }

//        System.out.println(area.getClass().getSimpleName());

        this.area = area;
        Platform.runLater(()->this.addPersonToGrid());
    }

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


    public void setPersonImageFile(ImageView personImageFile) {
        this.personImageFile = personImageFile;
        getChildren().add(this.personImageFile);
        personImageFile.toFront();
    }

    public void addPersonToGrid() {
        HotelBuilder.gridPane.add(this, this.getArea().getX(), this.getArea().getY());
    }

    public void removePersonFromGrid() {
        HotelBuilder.gridPane.getChildren().remove(this);
    }

    public LinkedList<Area> getMovingQueue() {
        return movingQueue;
    }

    public void setMovingQueue(LinkedList<Area> movingQueue) {
        this.movingQueue = movingQueue;
    }
}