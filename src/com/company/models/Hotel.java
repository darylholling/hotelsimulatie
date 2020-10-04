package com.company.models;

import com.company.actions.EventBuilder;
import com.company.actions.HotelBuilder;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Hotel extends Application {
    @Override
    public void start(Stage stage) {
        Settings settings = new Settings();
        HotelBuilder hotelBuilder = new HotelBuilder(stage);
        EventBuilder eventBuilder = new EventBuilder(settings);
        Time timer = new Time();

        Menu menu = new Menu(stage, settings, new ArrayList<>() {
            {
                add(hotelBuilder);
                add(eventBuilder);
                add(timer);
            }
        });
        menu.run();
    }
}