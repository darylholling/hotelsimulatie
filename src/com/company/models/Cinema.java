package com.company.models;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cinema extends Area {

    public Cinema (Position position, Dimensions dimensions) {
        super(position, dimensions);

//        this.setImageFile();
        ImageView imageView = new ImageView(new Image("/cinema.jpg"));
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        this.setImageFile(imageView);

    }
}