package com.company.models;

import com.company.models.areas.Area;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;

abstract public class Person extends StackPane implements MoveInterface, HTEListener {
    protected ImageView personImageFile;
    private Area area;
    protected LinkedList<Area> movingQueue = new LinkedList<>();

    public Area getArea() {
        return this.area;
    }

    public void setArea(Area area) {
        this.area = area;
//        this.area.getImageFile();
    }

    public void setPersonImage(Guest guest, String image) {
        ImageView personImageView = null;
        try {
            personImageView = new ImageView(new Image(new FileInputStream("src/com/company/images/" + image)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        personImageView.setFitHeight(42);
        personImageView.setFitWidth(18);
        personImageView.toFront();
        guest.setPersonImageFile(personImageView);
    }

    public void setPersonImageFile(ImageView personImageFile) {
        this.personImageFile = personImageFile;
        getChildren().add(this.personImageFile);
    }

    public LinkedList<Area> getMovingQueue() {
        return movingQueue;
    }

    public void setMovingQueue(LinkedList<Area> movingQueue) {
        this.movingQueue = movingQueue;
    }
}
