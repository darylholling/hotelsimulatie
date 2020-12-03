package com.company.models;

import com.company.actions.EventHandler;
import com.company.listeners.HTEListener;
import com.company.listeners.LateComingHTEListener;
import com.company.models.areas.*;
import com.company.events.CleaningEmergencyEvent;
import com.company.events.DefaultCleaningEvent;
import com.company.models.areas.Area;
import com.company.models.areas.Cinema;
import com.company.models.areas.Diner;
import com.company.models.areas.Lobby;
import com.company.persons.Cleaner;
import com.company.persons.Guest;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Hotel extends Application implements HTEListener {
    public ArrayList<Guest> guestList = new ArrayList<>();
    public ArrayList<Guest> activeGuestList = new ArrayList<>();
    public ArrayList<Area> areas = new ArrayList<>();
    public ArrayList<Cleaner> cleaners = new ArrayList<>();
    public Stage stage;
    public Hotel hotel = this;
    public Time timer;
    public Queue<CleaningEmergencyEvent> cleaningEmergencyEvents = new LinkedList<>();
    public Queue<DefaultCleaningEvent> defaultCleaningEvents = new LinkedList<>();
    public int currentHTE;
    public ArrayList<LateComingHTEListener> lateComingHTEListeners = new ArrayList<>();
    public Menu menu;
    public int hotelWidth;
    public int hotelHeight;
    public GridPane mainPane;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        HotelHandler hotelHandler = new HotelHandler(hotel);
        EventHandler eventHandler = new EventHandler(hotel);
        this.timer = new Time(new ArrayList<>() {
            {
                add(eventHandler);
                add(hotelHandler);
                add(hotel);
            }
        });
        Menu menu = new Menu(stage, new ArrayList<>() {
            {
                add(timer);
                add(hotelHandler);
                add(eventHandler);
            }
        });
        this.menu = menu;
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

    public Area[] getAllCinemas() {
        return hotel.areas.stream().filter(area -> area instanceof Cinema).toArray(Area[]::new);
    }

    public Area getDiner() {
        return this.areas.stream().filter(area -> area instanceof Diner).findFirst().orElse(null);
    }

    public Area getFitness() {
        return this.areas.stream().filter(area -> area instanceof Fitness).findFirst().orElse(null);
    }

    public int getCurrentHTE() {
        return this.currentHTE;
    }

    public void setScene(Scene scene) {
        hotel.stage.setScene(scene);
        hotel.stage.setResizable(false);
        hotel.stage.show();
    }

    public void addGuestToBothLists(Guest guest) {
        guestList.add(guest);
        activeGuestList.add(guest);
    }

    public void removeGuestFromActiveList(Guest guest) {
        activeGuestList.remove(guest);
    }

    public void createCleaners() {
        int cleanerCount = 2;

        for (int i = 0; i < cleanerCount; i++) {
            Area lobby = this.getLobby();
            if (lobby != null) {
                Cleaner cleaner = new Cleaner(hotel, lobby);
                this.cleaners.add(cleaner);
                hotel.lateComingHTEListeners.add(cleaner);
            }
        }
    }
}
