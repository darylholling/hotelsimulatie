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
import java.io.FileReader;
import java.io.IOException;

public class HotelBuilder extends Application {
    int maxX = 0;
    int maxY = 0;
    int totalMaxY = 0;
    int totalMaxX = 0;
    private File layoutFile;

    private Parent createContent() throws IOException {
        // size of window
        Pane root = new Pane();
        GridPane gridPane = new GridPane();

//        set layout file to run Hotelbuilder
        File layoutFile = new File("json/2roomlayout.json");

        Gson gson = new GsonBuilder().create();
        JsonArray jsonArrays = gson.fromJson(new FileReader("json/2roomlayout.json"), JsonArray.class);

//        JsonFactory layoutFactory = new JsonFactory();
//        Parser layoutInfo = layoutFactory.getParser(layoutFile.toString());
//        JsonInfo[] layouts = layoutInfo.readJson(layoutFile);

        for (JsonElement jsonElement : jsonArrays) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonObject position = jsonObject.get("position").getAsJsonObject();
            JsonObject dimensions = jsonObject.get("dimensions").getAsJsonObject();

            int layoutHeight = Integer.parseInt(position.get("y").getAsString()) + (Integer.parseInt(dimensions.get("height").getAsString()) - 1);
            if (maxY <= layoutHeight) {
                maxY = layoutHeight;
            }

            int layoutWidth = Integer.parseInt(position.get("x").getAsString()) + (Integer.parseInt(dimensions.get("width").getAsString()) - 1);
            if (maxX <= layoutWidth) {
                maxX = layoutWidth;
            }
        }

        totalMaxY = (maxY + 1);
        totalMaxX = (maxX + 2);

        //Building the Area's
        System.out.println(totalMaxX);
        System.out.println(totalMaxY);

        //TODO dimensionale array voor area waardes.
        Area[][] areas;

        for (int i = 0; i <= totalMaxX; i++) {
            for (int j = 0; j <= totalMaxY; j++) {
                this.createDefaultAreas(gridPane, i, j);
            }
        }

        this.createAreas(gridPane, jsonArrays);

        for (Node node : gridPane.getChildren()) {
            System.out.println(node.getClass());
        }

        return gridPane;
    }

    private void createDefaultAreas(GridPane gridPane, int i, int j) throws FileNotFoundException {
        Area area;

        if (i == 0) {
            area = new ElevatorShaft(i, j, 1, 1);
        } else if (j == totalMaxY && i == 1) {
            area = new Lobby(i, j, (maxX + 1), 1);
        } else if (i == totalMaxX) {
            area = new Stairs(i, j, 1, 1);
        } else {
            area = new Hallway(i, j, 1, 1);
        }
        if (area instanceof Lobby) {
            gridPane.add(area, i, j, maxX + 1, 1);
        } else if (!(area instanceof Hallway)) {
            gridPane.add(area, i, j);
        }
    }

    private Node getChildAtRowCol(GridPane gridPane, int row, int col) {
        for (Node child : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(child) == col && GridPane.getRowIndex(child) == row) {
                return child;
            }
        }
        return null;
    }

    //TODO function validate file


    private void createAreas(GridPane gridPane, JsonArray jsonArrays) throws FileNotFoundException {

        for (JsonElement jsonElement : jsonArrays) {
            Area area = null;
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
                    if (stars == 0) {
                        return;
                    }

                    System.out.println(x);
                    System.out.println(y);
                    System.out.println(width);
                    System.out.println(height);
                    System.out.println(stars);
                    area = new GuestRoom(x, y, width, height, stars);
                    break;
                case "diner":
                    if (capacity == 0) {
                        return;
                    }

                    area = new Diner(x, y, width, height, capacity);
                    break;
                case "fitness":
                    area = new Fitness(x, y, width, height);
                    break;
                case "movie":
                    area = new Cinema(x, y, width, height);
                    break;
                default:
                    System.out.println("invalid type");
            }
            //to start from bottom left
            if (area != null) {
                Node child = this.getChildAtRowCol(gridPane, (totalMaxY - area.getY()) - 1, area.getX() + 1);
//                Node child = this.getChildAtRowCol(gridPane, (totalMaxY - area.getPosition().getY())-1, area.getPosition().getX()+1);

                if (child != null) {
                    gridPane.getChildren().remove(child);
                }

                gridPane.add(area, area.getX() + 1, area.getY());

//                if (area.getDimensions().getHeight()>1){
//                    gridPane.add(area, area.getPosition().getX()+1, (totalMaxY - area.getPosition().getY())-area.getDimensions().getHeight(), area.getDimensions().width, area.getDimensions().height);
//                } else {
//                    gridPane.add(area, area.getPosition().getX() + 1, (totalMaxY - area.getPosition().getY()) - 1, area.getDimensions().width, area.getDimensions().height);
//                }
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