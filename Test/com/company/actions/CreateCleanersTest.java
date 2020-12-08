package com.company.actions;

import com.company.models.Hotel;
import com.company.models.areas.Area;
import com.company.models.areas.Lobby;
import com.company.persons.Cleaner;
import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class CreateCleanersTest {
    Hotel hotel = new Hotel();
    JFXPanel jfxPanel = new JFXPanel();

    @Test
    public void testCleanerCount() throws FileNotFoundException {
        Lobby lobby = new Lobby(1,1,1,1);
        hotel.areas.add(lobby);
        hotel.createCleaners();
        Assert.assertEquals(2, hotel.cleaners.size());
    }

}