package com.company.events;

import com.company.models.Hotel;
import com.company.models.LateComingHTEListener;

public class GodzillaEvent extends Event {
    //event is not being used due to time issues and priorities.
    public GodzillaEvent(Integer eventTime, Hotel hotel) {
        super(eventTime, hotel);
    }

    @Override
    public void fire() {
    }
}
