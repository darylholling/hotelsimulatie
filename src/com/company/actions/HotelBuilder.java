package com.company.actions;

import com.company.models.Hotel;
import com.company.models.Settings;
import com.company.models.areas.*;
import com.google.gson.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class HotelBuilder {
    public static GridPane gridPane;
    int maxXInJson = 0;
    int maxYInJson = 0;
    int hotelHeight = 0;
    int hotelWidth = 0;
    JsonArray jsonArrays;
    Area[][] areas;
    private Hotel hotel;

    //constructor
    public HotelBuilder(Hotel hotel) {
        this.hotel = hotel;
    }

    //creating hotel content including visualisation.
    public void createContent() throws FileNotFoundException {
        gridPane = new GridPane();

        File layoutFile = Settings.getSettings().getLayoutFile();

        Gson gson = new GsonBuilder().create();
        try {
            jsonArrays = gson.fromJson(Files.newBufferedReader(new File(String.valueOf(layoutFile)).toPath(), StandardCharsets.UTF_8), JsonArray.class);
        } catch (IOException | JsonParseException e) {
            hotel.timer.stopTimer();
            hotel.menu.addJsonError("hotelfile");
            hotel.setScene(new Scene(hotel.menu.mainMenuContent()));
        }

        for (JsonElement jsonElement : jsonArrays) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonObject position = jsonObject.get("position").getAsJsonObject();
            JsonObject dimensions = jsonObject.get("dimensions").getAsJsonObject();

            int layoutHeight = Integer.parseInt(position.get("y").getAsString()) + (Integer.parseInt(dimensions.get("height").getAsString()) - 1);
            if (maxYInJson <= layoutHeight) {
                maxYInJson = layoutHeight;
            }

            int layoutWidth = Integer.parseInt(position.get("x").getAsString()) + (Integer.parseInt(dimensions.get("width").getAsString()) - 1);
            if (maxXInJson <= layoutWidth) {
                maxXInJson = layoutWidth;
            }
        }

        hotelHeight = (maxYInJson + 1);
        hotelWidth = (maxXInJson + 2);

        hotel.hotelHeight = hotelHeight;
        hotel.hotelWidth = hotelWidth;

        areas = new Area[hotelWidth + 1][hotelHeight + 1];
        for (int x = 0; x <= hotelWidth; x++) {
            for (int y = 0; y <= hotelHeight; y++) {
                this.createDefaultAreas(gridPane, x, y, areas);
            }
        }

        this.createAreas(gridPane, jsonArrays, areas);
        this.createNeighbours(areas, hotelWidth, hotelHeight);

        for (Area[] areaList : areas) {
            for (Area area : areaList) {
                hotel.areas.add(area);
                area.setHotel(hotel);
            }
        }

        hotel.mainPane = gridPane;
    }

    //add neighbours for pathfinding
    private void createNeighbours(Area[][] areas, int hotelWidth, int hotelHeight) {
        for (int x = 1; x <= hotelWidth; x++) {
            for (int y = 0; y <= hotelHeight; y++) {
                Area currentArea = areas[x][y];

                if (x == 1) {
                    currentArea.addNeighbour(areas[currentArea.getX() + 1][currentArea.getY()], 1);

                    continue;
                }

                if (x == hotelWidth) {
                    currentArea.addNeighbour(areas[currentArea.getX() - 1][currentArea.getY()], 1);

                    if (y != hotelHeight) {
                        currentArea.addNeighbour(areas[currentArea.getX()][currentArea.getY() + 1], 1);
                    }
                    if (y != 0) {
                        currentArea.addNeighbour(areas[currentArea.getX()][currentArea.getY() - 1], 1);
                    }

                    continue;
                }

                currentArea.addNeighbour(areas[currentArea.getX() - 1][currentArea.getY()], 1);
                currentArea.addNeighbour(areas[currentArea.getX() + 1][currentArea.getY()], 1);
            }
        }
    }

    //create all default areas which is not defined in json file
    public void createDefaultAreas(GridPane gridPane, int i, int j, Area[][] areas) throws FileNotFoundException {
        Area area;

        int dimensionWidth = maxXInJson + 1;
        if (i == 0) {
            area = new ElevatorShaft(i, j, 1, 1);
        } else if (j == hotelHeight && i == 1) {
            area = new Lobby(i, j, dimensionWidth, 1);
        } else if (i == hotelWidth) {
            area = new Stairs(i, j, 1, 1);
        } else {
            area = new Hallway(i, j, 1, 1);
        }
        if (area instanceof Lobby) {
            gridPane.add(area, i, j, dimensionWidth, 1);
        } else if (!(area instanceof Hallway)) {
            gridPane.add(area, i, j);
        }

        areas[i][j] = area;
    }

    private Node getChildAtRowCol(GridPane gridPane, int row, int col) {
        for (Node child : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(child) == col && GridPane.getRowIndex(child) == row) {
                return child;
            }
        }
        return null;
    }


    private void createAreas(GridPane gridPane, JsonArray jsonArrays, Area[][] areas) throws FileNotFoundException {
        //ADD HALLWAY WHEN THERE'S AN EMPTY SPACE
        for (int i = 1; i < hotelWidth; i++) {
            for (int j = 0; j < hotelHeight; j++) {
                Area area = new Hallway(i, j, 1, 1);
                gridPane.add(area, i, j);
                areas[i][j] = area;
            }
        }

        for (JsonElement jsonElement : jsonArrays) {
            Area area = null;
            Area areaBackground;
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            JsonObject position = jsonObject.get("position").getAsJsonObject();
            int x = position.get("x").getAsInt();
            int y = position.get("y").getAsInt();

            JsonObject dimensions = jsonObject.get("dimensions").getAsJsonObject();
            int height = dimensions.get("height").getAsInt();
            int width = dimensions.get("width").getAsInt();

            JsonObject data = jsonObject.get("data").getAsJsonObject();
            int stars = 0;
            if (data.has("stars")) {
                stars = data.get("stars").getAsInt();
            }

            int capacity = 0;
            if (data.has("max")) {
                capacity = data.get("max").getAsInt();
            }

            int defaultY = (hotelHeight - y) - 1;
            int defaultX = x + 1;

            switch (jsonObject.get("type").getAsString()) {
                case "room":
                    area = new GuestRoom(defaultX, defaultY, width, height, stars);
                    break;
                case "diner":
                    area = new Diner(defaultX, defaultY, width, height, capacity);
                    break;
                case "fitness":
                    area = new Fitness(defaultX, defaultY, width, height);
                    break;
                case "movie":
                    area = new Cinema(defaultX, defaultY, width, height);
                    break;
                default:
                    System.out.println("invalid type");
            }

            if (area != null) {
                if (area.getAreaHeight() > 1) {
                    int backgroundY = height > 1 ? ((hotelHeight - y) - height) : defaultY;
                    areaBackground = area.createAreaBackground(defaultX, backgroundY, width, height, area.getImageFile());

                    gridPane.add(areaBackground, areaBackground.getX(), areaBackground.getY(), areaBackground.getAreaWidth(), areaBackground.getAreaHeight());
                    areas[areaBackground.getX()][areaBackground.getY()] = areaBackground;
                }

                Node child = this.getChildAtRowCol(gridPane, defaultY, defaultX);
                if (child != null) {
                    gridPane.getChildren().remove(child);
                }

                gridPane.add(area, defaultX, defaultY, area.getAreaWidth(), area.getAreaHeight());
                areas[defaultX][defaultY] = area;
            }
        }
    }
}