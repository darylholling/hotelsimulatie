package com.company.actions;

import com.company.models.Hotel;
import com.company.models.areas.Lobby;
import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.Test;


import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class CreateCleanersTest {
    Hotel hotel = new Hotel();
    CreateCleaners createCleaners = new CreateCleaners(hotel);
    JFXPanel jfxPanel = new JFXPanel();

    @Test
    public void testCleanerCount() throws FileNotFoundException {
        Lobby lobby = new Lobby(1,1,1,1);
        hotel.areas.add(lobby);

        createCleaners.create();

        Assert.assertEquals(hotel.cleaners.size(), createCleaners.cleanerCount);
    }



}