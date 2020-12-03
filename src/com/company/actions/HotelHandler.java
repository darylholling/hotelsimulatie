package com.company.actions;

import com.company.models.HTEListener;
import com.company.models.Hotel;
import com.company.models.StartListener;

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
        } catch (FileNotFoundException e ) {
            System.out.println("Failed to build hotel");
        }
    }

    @Override
    public void updatedHTE(int HTE) {
        hotelController.HTELabelUpdate();
    }
}

