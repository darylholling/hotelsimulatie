package com.company.models.areas;

import com.company.persons.Guest;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuestRoom extends Area {
    private int stars;
    private boolean isClean = true;

    public GuestRoom(int x, int y, int areaWidth, int areaHeight, int stars) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);
        this.stars = stars;

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

    public boolean isAvailable() {
        return isClean && !isOccupied();
    }

    public void setClean(boolean clean) {
        isClean = clean;
    }

    public boolean isOccupied() {
        return this.persons.stream().anyMatch(i -> i instanceof Guest);
    }

    public int getStars() {
        return stars;
    }
}
