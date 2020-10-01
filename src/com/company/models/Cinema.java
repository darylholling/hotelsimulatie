package com.company.models;


import com.company.models.Area;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Cinema extends Area {

    public Cinema(int x, int y, int areaWidth, int areaHeight) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);

//        this.setImageFile();
        ImageView imageView = new ImageView(new Image(new FileInputStream("src/com/company/images/cinema.jpg")));
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        //this.setImageFile(imageView);

    }
}