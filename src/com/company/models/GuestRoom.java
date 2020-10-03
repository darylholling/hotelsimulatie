package com.company.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuestRoom extends Area {
    private int stars;

    public GuestRoom(int x, int y, int areaWidth, int areaHeight, int stars) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);
        String filePath;
        switch (stars) {
            case 1:
                filePath = "1star.jpg";
                break;
            case 2:
                filePath = "2star.jpg";
                break;
            case 3:
                filePath = "3star.jpg";
                break;
            case 4:
                filePath = "4star.jpg";
                break;
            case 5:
                filePath = "5star.jpg";
                break;
            default:
                throw new RuntimeException("no image defined");
        }
        if (areaHeight == 1) {
            super.setDefaultImage(this, filePath, areaWidth);
        } else {
            super.setImageFile(new ImageView(new Image(new FileInputStream("src/com/company/images/" + filePath))));
        }
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
