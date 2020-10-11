package com.company.events;

import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.LateComingHTEListener;
import com.company.models.areas.Area;
import com.company.models.areas.Cinema;
import javafx.application.Platform;

public class StartCinemaEvent extends Event {
    private Guest guest;
    private int duration;
    private int endHte;
    public StartCinemaEvent(Integer eventTime, Hotel hotel, int duration) {
        super(eventTime, hotel);
        this.duration = duration;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void fire() {
        Cinema[] allCinemas = this.hotel.areas.stream().filter(area -> area instanceof Cinema).toArray(Cinema[]::new);
        for (Cinema cinema : allCinemas){
            cinema.isPlaying();
            System.out.println(cinema.isPlaying());
            endHte = hotel.currentHTE + this.duration;
        }
    }
    }

