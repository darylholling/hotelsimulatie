package com.company.events;

import com.company.persons.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Cinema;

public class StartCinemaEvent extends Event {
    private Guest guest;
    //due to time shortage this variable is not used yet.
    private int duration;
    private int endHte;

    public StartCinemaEvent(Integer eventTime, Hotel hotel, int duration) {
        super(eventTime, hotel);
        this.duration = duration;
    }

    //get guest
    public Guest getGuest() {
        return guest;
    }

    //set guest
    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    @Override
    //handles the notification received from FireableListener
    public void fire() {
        Cinema[] allCinemas = this.hotel.areas.stream().filter(area -> area instanceof Cinema).toArray(Cinema[]::new);

        for (Cinema cinema : allCinemas) {
            cinema.isPlaying();
            endHte = hotel.currentHTE + this.duration;
        }
    }
}

