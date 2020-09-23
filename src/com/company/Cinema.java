package com.company;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Cinema extends Area {
    private ArrayList<Person> cinemaGuests;

    public Cinema (Position position, Dimensions dimensions) {
        super(position, dimensions);

        this.setImageFile(this.determineImageFile());
    }

        public ImageView determineImageFile() {
            ImageView imageView = new ImageView(this.getImage());

            this.configureSize(imageView);

            return imageView;
        }

        public Image getImage() {
            String imageName = "Cinema.png";

            return new Image("/" + imageName);

    }
    public void configureSize(ImageView imageView) {
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

    }


}