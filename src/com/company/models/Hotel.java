package com.company.models;

import com.company.actions.EventHandler;
import com.company.actions.HotelBuilder;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Hotel extends Application {
    @Override
    public void start(Stage stage) {
        Settings settings = new Settings();
        HotelBuilder hotelBuilder = new HotelBuilder(stage);
        EventHandler eventHandler = new EventHandler(settings);
        Time timer = new Time(new ArrayList<>(){
            {
                add(eventHandler);
                add(hotelBuilder);
            }
        });

        Menu menu = new Menu(stage, settings, new ArrayList<>() {
            {
                add(hotelBuilder);
                add(eventHandler);
                add(timer);
            }
        });
        menu.run();
    }
}