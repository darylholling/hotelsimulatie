package com.company.models;

import com.company.actions.HotelBuilder;
import javafx.application.Application;
import javafx.stage.Stage;

public class Hotel extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Settings settings = new Settings();
        Menu menu = new Menu(stage, settings);
        menu.run();
    }
}