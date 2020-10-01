package com.company.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ElevatorShaft extends Area {
    private Elevator elevator;

    public ElevatorShaft(int x, int y, int areaWidth, int areaHeight) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);


        ImageView imageView = new ImageView(new Image(new FileInputStream("src/com/company/images/elevatorShaftClosed.jpg")));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        this.setImageFile(imageView);
    }
}