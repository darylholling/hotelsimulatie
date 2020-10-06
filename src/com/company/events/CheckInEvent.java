package com.company.events;

import com.company.models.Guest;

public class CheckInEvent extends Event {
    private int idGuest;
    private int stars;

    public CheckInEvent(Integer eventTime, int idGuest, int stars) {
        super(eventTime);
        this.idGuest = idGuest;
        this.stars = stars;
        Guest guest = new Guest();
        guest.setIdGuest(idGuest);
        guest.setPreferredStars(stars);

    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(int idGuest) {
        this.idGuest = idGuest;
    }

}
