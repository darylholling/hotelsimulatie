package com.company.actions;

import com.company.models.Cleaner;
import com.company.models.Hotel;
import com.company.models.StartListener;
import com.company.models.areas.Area;

import java.util.ArrayList;

public class CreateCleaners implements StartListener {
    private Hotel hotel;
    private ArrayList<Cleaner> cleaners = new ArrayList<>();

    public CreateCleaners(Hotel hotel) {
        this.hotel = hotel;
    }

    public void create() {
        int cleanerCount = 2;

        for (int i = 0; i < cleanerCount; i++) {
            Area lobby = hotel.getLobby();
            if (lobby != null) {
                Cleaner cleaner = new Cleaner(hotel, lobby);
                cleaner.setCleanerImage();
                this.cleaners.add(cleaner);
                hotel.lateComingHTEListeners.add(cleaner);
            }
        }

        hotel.cleaners = this.cleaners;
    }

    @Override
    public void handleStart() throws Exception {
        this.create();
    }
}
