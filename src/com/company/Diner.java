
package com.company;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Diner extends Area {

    private int capacity;

    public Diner (Position position, Dimensions dimensions, int capacity) {
        super(position, dimensions);

        this.capacity = capacity;

        //        this.setImageFile();
        ImageView imageView = new ImageView(new Image("/diner.jpg"));
        imageView.setFitHeight(50);
        imageView.setFitWidth(100);

        this.setImageFile(imageView);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;

    }
}
