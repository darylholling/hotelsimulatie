package com.company.actions;

import com.company.listeners.HTEListener;
import com.company.models.Hotel;
import com.company.listeners.StartListener;

import java.io.FileNotFoundException;

public class HotelHandler implements StartListener, HTEListener  {
    private Hotel hotel;
    private HotelBuilder hotelBuilder;
    private HotelController hotelController;

    public HotelHandler(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void handleStart() {
        this.initializeHotel();
    }

    private void initializeHotel() {
        hotelBuilder = new HotelBuilder(hotel);
        hotelController = new HotelController(hotel);

        try {
            hotelBuilder.createContent();
            hotelController.createContent();
            hotel.createCleaners();
            hotel.timer.startTimer();
        } catch (FileNotFoundException e ) {
            System.out.println("Failed to build hotel");
        }
    }

    @Override
    public void updatedHTE(int HTE) {
        hotelController.HTELabelUpdate();
    }
}

