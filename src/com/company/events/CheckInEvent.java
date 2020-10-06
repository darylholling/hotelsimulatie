package com.company.events;

import com.company.models.Guest;
import com.company.models.Hotel;

public class CheckInEvent extends Event {
    private int id;
    private int stars;
    private Hotel hotel;

    public CheckInEvent(Hotel hotel, Integer eventTime, int idGuest, int stars) {
        super(eventTime, hotel);
        this.id = idGuest;
        this.stars = stars;
        this.hotel = hotel;
    }

    @Override
    public void fire() {
        //TODO check if room with stars is available, otherwise upgrade. If upgrade not possible create checkout event.
        if (false == false) {
            return;
        }

        Guest guest = new Guest();
        guest.setId(id);
        guest.setPreferredStars(stars);
        this.hotel.guestList.add(guest);

        //TODO go to room
    }
}
