package com.company;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Hallway extends Area {
    public Hallway(int x, int y, int areaWidth, int areaHeight) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);

        ImageView imageView = new ImageView(new Image(new FileInputStream("src/com/company/images/hallway.jpg")));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        this.setImageFile(imageView);
    }
}


