package com.company;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Fitness extends Area {

      public Fitness (Position position, Dimensions dimensions) throws FileNotFoundException {
        super(position, dimensions);

          //        this.setImageFile();
          ImageView imageView = new ImageView(new Image(new FileInputStream("src/com/company/images/fitness.jpg")));
          imageView.setFitHeight(80);
          imageView.setFitWidth(160);

          this.setImageFile(imageView);

    }

}