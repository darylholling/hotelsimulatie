package com.company;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Hallway extends Area {
    public Hallway(Position position, Dimensions dimensions) throws FileNotFoundException {
        super(position, dimensions);

        ImageView imageView = new ImageView(new Image(new FileInputStream("src/com/company/images/hallway.jpg")));
        imageView.setFitHeight(80);
        imageView.setFitWidth(80);

        this.setImageFile(imageView);
    }
}


