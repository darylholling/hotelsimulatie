package com.company;

import com.google.gson.*;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class HotelBuilder extends Application {
    int maxXInJson = 0;
    int maxYInJson = 0;
    int hotelHeight = 0;
    int hotelWidth = 0;
    private File layoutFile;
    JsonArray jsonArrays;
    GridPane gridPane;

    private Parent createContent() throws IOException {
        // size of window
        Pane root = new Pane();
        gridPane = new GridPane();

//        set layout file to run Hotelbuilder
//        File layoutFile = new File("src/com/company/files/layout.json");
        File layoutFile = new File("json/2roomlayout.json");

        Gson gson = new GsonBuilder().create();
        jsonArrays = gson.fromJson(Files.newBufferedReader(new File(String.valueOf(layoutFile)).toPath(), StandardCharsets.UTF_8), JsonArray.class);

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

        Area[][] areas = new Area[hotelWidth + 1][hotelHeight + 1];

        for (int x = 0; x <= hotelWidth; x++) {
            for (int y = 0; y <= hotelHeight; y++) {
                this.createDefaultAreas(gridPane, x, y, areas);
            }
        }

        this.createAreas(gridPane, jsonArrays, areas);

        return gridPane;
    }

    private void createDefaultAreas(GridPane gridPane, int i, int j, Area[][] areas) throws FileNotFoundException {
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

        System.out.println("I:" + i + "- Y:" + j);
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
        for (int i = 1; i < hotelWidth; i++){
            for (int j = 0; j < hotelHeight; j++){
                    Area area = new Hallway(i,j, 1, 1);
                    gridPane.add(area, i, j, 1, 1);
            }
        }

        for (JsonElement jsonElement : jsonArrays) {
            Area area = null;
            Area areaBackground = null;
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            JsonObject position = jsonObject.get("position").getAsJsonObject();
            int x = Integer.parseInt(position.get("x").getAsString());
            int y = Integer.parseInt(position.get("y").getAsString());

            JsonObject dimensions = jsonObject.get("dimensions").getAsJsonObject();
            int height = Integer.parseInt(dimensions.get("height").getAsString());
            int width = Integer.parseInt(dimensions.get("width").getAsString());

            JsonObject data = jsonObject.get("data").getAsJsonObject();
            int stars = 0;
            if (data.has("stars")) {
                stars = Integer.parseInt(data.get("stars").getAsString());
            }

            int capacity = 0;
            if (data.has("max")) {
                capacity = Integer.parseInt(data.get("max").getAsString());
            }

            switch (jsonObject.get("type").getAsString()) {
                case "room":
                    area = new GuestRoom(x, y, width, height, stars);
                    if(height > 1) {
                        areaBackground = new GuestRoomBackground(x, y, width, height, stars);
                    }
                    break;
                case "diner":
                    area = new Diner(x, y, width, height, capacity);
                    break;
                case "fitness":
                    area = new Fitness(x, y, width, height);
                    break;
                case "movie":
                    area = new Cinema(x, y, width, height);
                    if(height > 1) {
                        areaBackground = new CinemaBackground(x, y, width, height);
                    }
                    break;
                default:
                    System.out.println("invalid type");
            }
            if (area != null) {
                // fixedY to take into account an area with a height > 1
                int defaultY = (hotelHeight - area.getY()) - 1;
                int fixedY = area.getAreaHeight() > 1 ? ((hotelHeight - area.getY()) - area.getAreaHeight()) : defaultY;

                Node child = this.getChildAtRowCol(gridPane, defaultY, area.getX()+1);
                if (child != null) {
                    gridPane.getChildren().remove(child);
                }

                if(areaBackground != null) {
                    gridPane.add(areaBackground, areaBackground.getX() +1, fixedY, areaBackground.getAreaWidth(), areaBackground.getAreaHeight());
                }

                areas[area.getX() + 1][defaultY] = area;

                gridPane.add(area, area.getX() +1, defaultY, area.getAreaWidth(), area.getAreaHeight());
            }
        }
    }
    public void setFiles(File file) {
        this.layoutFile = file;
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.setResizable(false);
        stage.show();
    }
}