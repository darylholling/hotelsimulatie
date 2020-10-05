package com.company.events;

public class CleaningEmergencyEvent extends Event {
    private int idGuest;

    public CleaningEmergencyEvent(Integer eventTime, int idGuest) {
        super(eventTime);
        this.idGuest = idGuest;
    }

    public int getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(int idGuest) {
        this.idGuest = idGuest;
    }

}
