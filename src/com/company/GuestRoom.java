package com.company;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuestRoom extends Area {
    private int stars;

    public GuestRoom (Position position, Dimensions dimensions, int stars) throws FileNotFoundException {
        super(position, dimensions);

        this.stars = stars;
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

        this.configureSize(imageView);

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

    public void configureSize(ImageView imageView) {
        switch (this.getDimensions().getWidth()) {
            case 1:
                imageView.setFitWidth(50);
                break;
            case 2:
                imageView.setFitWidth(100);
                break;
        }

        switch (this.getDimensions().getHeight()) {
            case 1:
                imageView.setFitHeight(50);
                break;
            case 2:
                imageView.setFitHeight(100);
                break;
        }
    }
}
