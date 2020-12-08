package com.company.actions;

import java.io.File;
import org.junit.Assert;
import java.util.HashMap;
import java.io.IOException;
import com.company.models.Hotel;
import com.company.models.Settings;
import com.company.models.areas.Area;
import java.io.FileNotFoundException;
import javafx.scene.layout.GridPane;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Test;


class HotelBuilderTest {
    Hotel hotel = new Hotel();
    HotelBuilder hotelBuilder = new HotelBuilder(hotel);
    JFXPanel jfxPanel = new JFXPanel();


    @Test
    public void checkIfAreasAreAddedToHotel() throws IOException {

        Settings.getSettings().setLayoutFile(new File("Test/jsonTestFiles/layoutTest.json"));
        int testCounter = 0;

        //Without created content
        for (int i = 0; i < hotel.areas.size(); i++) {
            if (hotel.areas.get(i) != null) {
                testCounter++;
            }
        }
        Assert.assertEquals(0,testCounter);

        //Create content
        hotelBuilder.createContent();

        //With created content
        for (int i = 0; i < hotel.areas.size(); i++) {
            if (hotel.areas.get(i) != null) {
                testCounter ++;
            }
        }
        Assert.assertEquals(72,testCounter);
    }

    @Test
    public void testIfDefaultAreasAreSetCorrectly() throws FileNotFoundException {
        GridPane gridPane = new GridPane();
        Settings.getSettings().setLayoutFile(new File("Test/jsonTestFiles/layoutTest.json"));
        hotelBuilder.createContent();

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
        Assert.assertEquals("ElevatorShaft", areas[0][0].getClass().getSimpleName());
        Assert.assertEquals("Stairs", areas[6][5].getClass().getSimpleName());
        Assert.assertEquals("Lobby", areas[1][5].getClass().getSimpleName());
        Assert.assertEquals("Hallway", areas[1][1].getClass().getSimpleName());

    }

    @Test
    public void checkIfNeighboursAreSetCorrectly() throws IOException {

        Settings.getSettings().setLayoutFile(new File("Test/jsonTestFiles/layoutTest.json"));
        hotelBuilder.createContent();
        for (int i = 0; i < hotel.areas.size(); i++) {
            HashMap neighbours = hotel.areas.get(i).getNeighbours();

            if (hotel.areas.get(i).getClass().getSimpleName().equals("ElevatorShaft")) {
                Assert.assertEquals(0, hotel.areas.get(i).getNeighbours().size());
            }
            if (hotel.areas.get(i).getX() == 1 && hotel.areas.get(i).getY() == 1) {
                Assert.assertEquals(1, neighbours.size());
            }
            if (hotel.areas.get(i).getX() == 7 && hotel.areas.get(i).getY() == 4) {
                Assert.assertEquals(3, neighbours.size());
            }
        }
    }
}