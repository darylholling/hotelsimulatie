package com.company.actions;

import com.company.models.Hotel;
import com.company.models.HteCounter;
import com.company.models.Settings;
import com.company.models.areas.Area;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


class HotelBuilderTest {
    Hotel hotel = new Hotel();
    HotelBuilder hotelBuilder = new HotelBuilder(hotel);
    GridPane gridPane = new GridPane();
    Settings settings = new Settings();
    JFXPanel jfxPanel = new JFXPanel();


    @Test
    public void testIfContentGetsCreatedAndReturnsHotelPane() throws IOException {
        Assert.assertEquals(VBox.class, hotelBuilder.createContent().getClass());
    }

    @Test
    public void checkIfAreasAreAddedToHotel() throws IOException {
        boolean isArea = false;

        //Without created content
        for (int i = 0; i < hotel.areas.size(); i++) {
            if (hotel.areas.get(i) instanceof Area) {
                isArea = true;
            } else {
                isArea = false;
            }
        }
        Assert.assertFalse(isArea);

        //Create content
        settings.setLayoutFile(new File("Test/jsonTestFiles/layoutTest.json"));
        hotelBuilder.createContent();

        //With created content
        for (int i = 0; i < hotel.areas.size(); i++) {
            if (hotel.areas.get(i) instanceof Area) {
                isArea = true;
            } else {
                isArea = false;
            }
        }
        Assert.assertTrue(isArea);
    }

    @Test
    public void testIfDefaultAreasAreSetCorrectly() throws FileNotFoundException {
        hotelBuilder.maxXInJson = 4;
        hotelBuilder.maxYInJson = 4;
        hotelBuilder.hotelHeight = 5;
        hotelBuilder.hotelWidth = 6;
        Area[][] areas = new Area[hotelBuilder.hotelWidth + 1][hotelBuilder.hotelHeight + 1];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                hotelBuilder.createDefaultAreas(gridPane, i, j, areas);

            }
        }

        String expectedElevatorShaft = "ElevatorShaft";
        String expectedStairs = "Stairs";
        String expectedLobby = "Lobby";
        String expectedHallway = "Hallway";

        Assert.assertEquals(expectedElevatorShaft, areas[0][0].getClass().getSimpleName());
        Assert.assertEquals(expectedStairs, areas[6][5].getClass().getSimpleName());
        Assert.assertEquals(expectedLobby, areas[1][5].getClass().getSimpleName());
        Assert.assertEquals(expectedHallway, areas[1][1].getClass().getSimpleName());

    }
}