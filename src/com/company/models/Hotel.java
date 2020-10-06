package com.company.models;

import com.company.actions.CreateCleaners;
import com.company.actions.EventHandler;
import com.company.actions.HotelBuilder;
import com.company.events.Event;
import com.company.models.areas.Area;
import com.company.models.areas.Lobby;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

public class Hotel extends Application implements HTEListener {
    public ArrayList<Guest> guestList = new ArrayList<>();
    public ArrayList<Area> areas = new ArrayList<>();
    public ArrayList<Cleaner> cleaners = new ArrayList<>();
    public Hotel hotel = this;
    private int currentHTE;

    @Override
    public void start(Stage stage) {
        Settings settings = new Settings();
        HotelBuilder hotelBuilder = new HotelBuilder(stage, hotel);
        CreateCleaners createCleaners = new CreateCleaners(hotel);
        EventHandler eventHandler = new EventHandler(settings, hotel);
        Time timer = new Time(new ArrayList<>() {
            {
                add(eventHandler);
                add(hotelBuilder);
                add(hotel);
            }
        });

        Menu menu = new Menu(stage, settings, new ArrayList<>() {
            {
                add(hotelBuilder);
                add(eventHandler);
                add(createCleaners);
                add(timer);
            }
        });
        menu.run();
    }

    @Override
    public void updatedHTE(int HTE) {
        this.currentHTE = HTE;
    }

    public Area getLobby() {
        return this.areas.stream().filter(area -> area instanceof Lobby).findFirst().orElse(null);
    }
}
