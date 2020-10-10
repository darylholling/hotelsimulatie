package com.company.actions;

import com.company.models.Cleaner;
import com.company.models.Hotel;
import com.company.models.StartListener;
import com.company.models.areas.Area;

import java.util.ArrayList;

public class CreateCleaners implements StartListener {
    Hotel hotel;
    int cleanerCount = 2;
    private ArrayList<Cleaner> cleaners = new ArrayList<>();

    public CreateCleaners(Hotel hotel) {
        this.hotel = hotel;
    }

    public void create() {
        for (int i = 0; i < cleanerCount; i++) {
            Area lobby = hotel.getLobby();
            if (lobby != null) {
                Cleaner cleaner = new Cleaner(hotel, lobby, hotel.cleaningEmergencyEvents, hotel.cleaningEvents);
                this.cleaners.add(cleaner);
            }
        }

        hotel.cleaners = this.cleaners;
    }

    @Override
    public void handleStart() throws Exception {
        this.create();
    }

    public ArrayList<Cleaner> getCleaners() {
        return cleaners;
    }
}
