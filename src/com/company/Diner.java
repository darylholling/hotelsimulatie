package com.company;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Diner extends Area {

    private int capacity;

    public Diner (Position position, Dimensions dimensions, int capacity) throws FileNotFoundException {
        super(position, dimensions);

        this.capacity = capacity;

        //        this.setImageFile();
        ImageView imageView = new ImageView(new Image(new FileInputStream("src/com/company/images/diner.jpg")));
        imageView.setFitHeight(80);
        imageView.setFitWidth(160);

        this.setImageFile(imageView);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;

    }
}
