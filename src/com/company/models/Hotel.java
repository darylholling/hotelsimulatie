package com.company.models;

import com.company.actions.CreateCleaners;
import com.company.actions.Dijkstra;
import com.company.actions.EventHandler;
import com.company.actions.HotelBuilder;
import com.company.events.CleaningEmergencyEvent;
import com.company.events.CleaningEvent;
import com.company.models.areas.Area;
import com.company.models.areas.GuestRoom;
import com.company.models.areas.Lobby;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Queue;

public class Hotel extends Application implements HTEListener {
    public ArrayList<Guest> guestList = new ArrayList<>();
    public ArrayList<Area> areas = new ArrayList<>();
    public ArrayList<Cleaner> cleaners = new ArrayList<>();
    public Settings settings = new Settings();
    public Stage stage;
    public Hotel hotel = this;
    public Dijkstra dijkstra = new Dijkstra();
    public Time timer;
    private int currentHTE;

//    public Queue<CleaningEmergencyEvent> cleaningEmergencyEvents;
//    public Queue<CleaningEvent> cleaningEvents;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        HotelBuilder hotelBuilder = new HotelBuilder(hotel);
        CreateCleaners createCleaners = new CreateCleaners(hotel);
        EventHandler eventHandler = new EventHandler(hotel);
        Time timer = new Time(new ArrayList<>() {
            {
                add(eventHandler);
                add(hotelBuilder);
                add(hotel);
            }
        }, this.settings);

        this.timer = timer;

        Menu menu = new Menu(stage, this.settings, new ArrayList<>() {
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

    public Guest getGuestById(int id) {
        return this.guestList.stream().filter(guest -> guest.getId() == id).findFirst().orElse(null);
    }
}
