package com.company.actions;

import com.company.models.Hotel;
import com.company.models.Settings;
import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class HotelControllerTest {
    Hotel hotel = new Hotel();
    EventBuilder eventBuilder = new EventBuilder();
    JFXPanel jfxPanel = new JFXPanel();

    @Test
    public void checkIfTheHighestHteIsSetToHTEBoard() throws IOException {

        eventBuilder.readJson(hotel);
        int highestHTE = Settings.getSettings().getHighestHteInJsonFile();
        Assert.assertEquals(120, highestHTE);
    }
}






















