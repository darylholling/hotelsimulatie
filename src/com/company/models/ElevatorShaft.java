package com.company.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ElevatorShaft extends Area {

    private Elevator elevator;

    public ElevatorShaft(Position position, Dimensions dimensions, Elevator elevator) {
        super(position, dimensions);

        this.elevator = elevator;

        ImageView imageView = new ImageView(new Image("/hallway.jpg"));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        this.setImageFile(imageView);
    }
}