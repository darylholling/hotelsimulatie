package com.company;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Cinema extends Area {

    public Cinema (Position position, Dimensions dimensions) throws FileNotFoundException {
        super(position, dimensions);

//        this.setImageFile();
        ImageView imageView = new ImageView(new Image(new FileInputStream("src/com/company/images/cinema.jpg")));
        imageView.setFitHeight(160);
        imageView.setFitWidth(160);

        this.setImageFile(imageView);

    }
}