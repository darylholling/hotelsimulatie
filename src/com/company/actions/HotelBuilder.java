package com.company.actions;

import com.company.models.*;
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
    JsonArray jsonArrays;
    GridPane gridPane;
    Area[][] areas;
    private File layoutFile;
    private Time time;

    private Parent createContent() throws IOException {
        // size of window
        Pane root = new Pane();
        gridPane = new GridPane();

//        set layout file to run Hotelbuilder
        File layoutFile = new File("src/com/company/files/layout.json");
//        File layoutFile = new File("json/2roomlayout.json");

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


        areas = new Area[hotelWidth + 1][hotelHeight + 1];
        for (int x = 0; x <= hotelWidth; x++) {
            for (int y = 0; y <= hotelHeight; y++) {
                this.createDefaultAreas(gridPane, x, y, areas);
            }
        }

        this.createAreas(gridPane, jsonArrays, areas);

//        for (Area[] areaArray : this.areas) {
//            for (Area area : areaArray) {
//                System.out.println(area);
//            }
//        }

        for (int x = 1; x <= hotelWidth; x++) {
            for (int y = 0; y <= hotelHeight; y++) {
                Area currentArea = areas[x][y];

                if (x == 1) {
                    currentArea.addNeighbour(areas[currentArea.getX() + 1][currentArea.getY()], 1);

                    continue;
                }

                if (x == hotelWidth) {
                    currentArea.addNeighbour(areas[currentArea.getX() -1][currentArea.getY()], 1);

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
                    System.out.println("name"+ area.getClass().getSimpleName());
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

    public void setFiles(File file) {
        this.layoutFile = file;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.setResizable(false);
        stage.show();

        Dijkstra ds = new Dijkstra();
        areas[1][0].setDistance(0);
        System.out.println(ds.findPath(areas[1][0],areas[3][2]));
//        new DijkstraTest(areas, hotelWidth, hotelHeight);
//        time.startTimer();
    }
}