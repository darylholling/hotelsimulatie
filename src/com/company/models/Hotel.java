package com.company.models;

import com.company.actions.CreateCleaners;
import com.company.actions.EventHandler;
import com.company.actions.HotelBuilder;
import com.company.models.areas.*;
import com.company.events.CleaningEmergencyEvent;
import com.company.events.DefaultCleaningEvent;
import com.company.models.areas.Area;
import com.company.models.areas.Cinema;
import com.company.models.areas.Diner;
import com.company.models.areas.Lobby;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Hotel extends Application implements HTEListener {
    public ArrayList<Guest> guestList = new ArrayList<>();
    public ArrayList<Area> areas = new ArrayList<>();
    public ArrayList<Cleaner> cleaners = new ArrayList<>();
    public Settings settings = new Settings();
    public Stage stage;
    public Hotel hotel = this;
    public Time timer;
    public Queue<CleaningEmergencyEvent> cleaningEmergencyEvents = new LinkedList<>();
    public Queue<DefaultCleaningEvent> defaultCleaningEvents = new LinkedList<>();
    public int currentHTE;
    public ArrayList<LateComingHTEListener> lateComingHTEListeners = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        HotelBuilder hotelBuilder = new HotelBuilder(hotel);
        CreateCleaners createCleaners = new CreateCleaners(hotel);
        EventHandler eventHandler = new EventHandler(hotel);
        this.timer = new Time(new ArrayList<>() {
            {
                add(eventHandler);
                add(hotelBuilder);
                add(hotel);
            }
        }, this.settings);

        Menu menu = new Menu(stage, this.settings, new ArrayList<>() {
            {
                add(hotelBuilder);
                add(createCleaners);
                add(eventHandler);
                add(timer);
            }
        });
        menu.run();
    }

    @Override
    public void updatedHTE(int HTE) {
        this.currentHTE = HTE;

        for (LateComingHTEListener lateComingHTEListener : lateComingHTEListeners) {
            lateComingHTEListener.updatedHTE(HTE);
        }
    }

    public Area getLobby() {
        return this.areas.stream().filter(area -> area instanceof Lobby).findFirst().orElse(null);
    }

    public Guest getGuestByNumber(int number) {
        return this.guestList.stream().filter(guest -> guest.getGuestNumber() == number).findFirst().orElse(null);
    }

    public Area getCinema() {
        return this.areas.stream().filter(area -> area instanceof Cinema).findFirst().orElse(null);
    }

    public Area getDiner() {
        return this.areas.stream().filter(area -> area instanceof Diner).findFirst().orElse(null);
    }

    public int getCurrentHTE() {
        return this.currentHTE;
    }
    public Area getFitness() {
        return this.areas.stream().filter(area -> area instanceof Fitness).findFirst().orElse(null);
    }
    public Stage getStage(){
        return this.stage;
    }
}
