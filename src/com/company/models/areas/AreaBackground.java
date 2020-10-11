package com.company.models.areas;

import javafx.scene.image.ImageView;

public class AreaBackground extends Area {
    ImageView imageView;

    public AreaBackground(int x, int y, int areaWidth, int areaHeight, ImageView filename) {
        super(x, y, areaWidth, areaHeight);
        imageView = filename;
        imageView.setFitHeight(50 * areaHeight);
        imageView.setFitWidth(50 * areaWidth);
        this.setImageFile(imageView);
    }
}
