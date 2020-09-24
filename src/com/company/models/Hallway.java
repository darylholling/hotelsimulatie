package com.company.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Hallway extends Area {
    public Hallway(Position position, Dimensions dimensions) {
        super(position, dimensions);

        ImageView imageView = new ImageView(new Image("/hallway.jpg"));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        this.setImageFile(imageView);
    }
}


