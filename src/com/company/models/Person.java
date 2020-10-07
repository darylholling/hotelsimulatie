package com.company.models;

import com.company.models.areas.Area;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

abstract public class Person extends Pane implements MoveInterface{
    private Area area;
    protected ImageView personImageFile;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
        this.area.getImageFile();
    }

    public void setPersonImage(Guest guest, String image)  {
        ImageView imageView = null;
        try {
            imageView = new ImageView(new Image(new FileInputStream("src/com/company/images/" + image)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        imageView.setFitHeight(42);
        imageView.setFitWidth(18);
        guest.setPersonImageFile(imageView);
    }

    public void setPersonImageFile(ImageView personImageFile) {
        this.personImageFile = personImageFile;
        getChildren().add(this.personImageFile);
    }

}
