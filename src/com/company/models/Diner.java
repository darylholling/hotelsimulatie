package com.company.models;

import com.company.models.Area;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Diner extends Area {
    private int capacity;

    public Diner(int x, int y, int areaWidth, int areaHeight, int capacity) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);

        this.capacity = capacity;

        //        this.setImageFile();
        ImageView imageView = new ImageView(new Image(new FileInputStream("src/com/company/images/diner.jpg")));
        imageView.setFitHeight(50*areaHeight);
        imageView.setFitWidth(50*areaWidth);

        this.setImageFile(imageView);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
