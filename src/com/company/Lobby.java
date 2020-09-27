package com.company;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Lobby extends Area {


    public Lobby (Position position, Dimensions dimensions) throws FileNotFoundException {
        super(position, dimensions);
        HotelBuilder builder = new HotelBuilder();
        ImageView imageView = new ImageView(new Image(new FileInputStream("src/com/company/images/lobby.jpg")));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50*(builder.maxWidth));

        this.setImageFile(imageView);

    }
}






