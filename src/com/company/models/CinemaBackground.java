package com.company.models;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CinemaBackground extends Area {

    public CinemaBackground(int x, int y, int areaWidth, int areaHeight) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);

        ImageView imageView = new ImageView(new Image(new FileInputStream("src/com/company/images/cinema.jpg")));
        imageView.setFitHeight(50*areaHeight);
        imageView.setFitWidth(50*areaWidth);

        this.setImageFile(imageView);

    }
}