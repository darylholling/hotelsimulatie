package com.company.models;

import com.company.models.Area;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuestRoom extends Area {
    private int stars;

    public GuestRoom(int x, int y, int areaWidth, int areaHeight, int stars) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);

        this.stars = stars;

        if(areaHeight > 1) { // any rooms with a height > 1 will have a separate background via GuestRoomBackground
            return;
        }
        this.setImageFile(this.determineImageFile());
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public ImageView determineImageFile() throws FileNotFoundException {
        ImageView imageView = new ImageView(this.getImage());

        imageView.setFitHeight(50*getAreaHeight());
        imageView.setFitWidth(50*getAreaWidth());

        return imageView;
    }

    public Image getImage() throws FileNotFoundException {
        String imageName;

        switch (this.getStars()) {
            case 1:
                imageName = "1star.jpg";
                break;
            case 2:
                imageName = "2star.jpg";
                break;
            case 3:
                imageName = "3star.jpg";
                break;
            case 4:
                imageName = "4star.jpg";
                break;
            case 5:
                imageName = "5star.jpg";
                break;
            default:
                throw new RuntimeException("no image defined");
        }

        return new Image(new FileInputStream("src/com/company/images/" + imageName));
    }
}
