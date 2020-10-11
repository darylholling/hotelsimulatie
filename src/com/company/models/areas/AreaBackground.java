package com.company.models.areas;

import javafx.scene.image.ImageView;

public class AreaBackground extends Area {
    public AreaBackground(int x, int y, int areaWidth, int areaHeight, ImageView filename) {
        super(x, y, areaWidth, areaHeight);
        filename.setFitHeight(50 * areaHeight);
        filename.setFitWidth(50 * areaWidth);
        this.setImageFile(filename);
    }
}
