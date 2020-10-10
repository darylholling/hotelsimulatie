package com.company.models;

import com.company.actions.HotelBuilder;
import com.company.models.areas.Area;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    //the HBox cus ImageView is still giving error messages PLEASE DO NOT REMOVE YET, WE MIGHT NEED THIS AS AN EMERGENCY SOLUTION
//        HBox imageBox = new HBox();
//        imageBox.maxHeight(49);
//        imageBox.maxWidth(48);
//        Label stickFigure = new Label();
//        Font font = Font.font("Verdana", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 11);
//        stickFigure.setText("   O\n   \\/\n   /\\");
//        stickFigure.setTextAlignment(TextAlignment.CENTER);
//        stickFigure.setFont(font);
//        imageBox.getChildren().add(stickFigure);
//        HotelBuilder.gridPane.add(imageBox, this.getArea().getX(), this.getArea().getY());
//    }


//    public void setPersonImage(Person person, String image)  {
//                ImageView personImageView = null;
//        try {
//            personImageView = new ImageView(new Image(new FileInputStream("src/com/company/images/" + image)));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        personImageView.setFitHeight(42);
//        personImageView.setFitWidth(18);
//        personImageView.toFront();
//        person.setPersonImageFile(personImageView);
//        System.out.println("Hi it's me 2");
//    }


    public void setPersonImageFile(ImageView personImageFile) {
        this.personImageFile = personImageFile;
        getChildren().add(this.personImageFile);
        personImageFile.toFront();
    }

    public void addPersonToGrid() {
        HotelBuilder.gridPane.add(this, this.getArea().getX(), this.getArea().getY());
    }

    public void removePersonFromGrid() {
//        this.personImageFile = personImageFile;
//        getChildren().remove(this.personImageFile);
//        personImageFile.toFront();
        HotelBuilder.gridPane.getChildren().remove(this);
    }

    public LinkedList<Area> getMovingQueue() {
        return movingQueue;
    }

    public void setMovingQueue(LinkedList<Area> movingQueue) {
        this.movingQueue = movingQueue;
    }
}