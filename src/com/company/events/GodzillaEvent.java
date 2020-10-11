package com.company.events;

import com.company.actions.HotelBuilder;
import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.LateComingHTEListener;
import com.company.models.areas.Area;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GodzillaEvent extends Event implements LateComingHTEListener {
    public Scene godzillaScene;
    private Guest guest;
    HotelBuilder hotelBuilder;
    Hotel hotel;
    public GodzillaEvent(Integer eventTime, Hotel hotel) {
        super(eventTime, hotel);

    }
    public Pane Godzilla() {
        Pane godzillaPane = new Pane();
        ImageView godzillaImage = null;

        try {
            godzillaImage = new ImageView(new Image(new FileInputStream("src/com/company/images/godzilla.gif")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        godzillaPane.setPrefWidth(500);
        godzillaPane.setPrefHeight(500);
        godzillaPane.getChildren().add(godzillaImage);

        return godzillaPane;
    }
    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    @Override
    public synchronized void fire() {
        godzillaScene = new Scene(Godzilla());
        hotelBuilder.getStage().setScene(godzillaScene);
    }

    @Override
    public void updatedHTE(int HTE) {
    }
}
