package com.company.actions;

import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;
import com.company.models.areas.ElevatorShaft;
import com.company.models.areas.GuestRoom;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class HotelBuilderTest {
    Hotel hotel = new Hotel();
    HotelBuilder hotelBuilder = new HotelBuilder(hotel);
    GridPane gridPane = new GridPane();
//    Area[][] areas = new Area[][];


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