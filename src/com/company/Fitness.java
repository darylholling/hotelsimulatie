package com.company;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Fitness extends Area {

      public Fitness (Position position, Dimensions dimensions) {
        super(position, dimensions);

          //        this.setImageFile();
          ImageView imageView = new ImageView(new Image("/fitness.jpg"));
          imageView.setFitHeight(100);
          imageView.setFitWidth(100);

          this.setImageFile(imageView);

    }

}