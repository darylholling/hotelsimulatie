package com.company.actions;

import com.company.models.*;
import com.company.models.areas.*;
import com.google.gson.*;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;

public class HotelBuilder implements StartListener, HTEListener {
    public static GridPane gridPane;
    int maxXInJson = 0;
    int maxYInJson = 0;
    int hotelHeight = 0;
    int hotelWidth = 0;
    JsonArray jsonArrays;
    Area[][] areas;
    Scene mainScene;
    private Label hteInfoBoard;
    private Hotel hotel;

    public HotelBuilder(Hotel hotel) {
        this.hotel = hotel;
    }

    public void start(Stage stage) throws Exception {
        mainScene = new Scene(createContent());
        stage.setScene(mainScene);
        stage.setResizable(false);
        stage.show();
    }

    public Parent createContent() throws IOException {
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
        this.createNeighbours(areas, hotelWidth, hotelHeight);

        VBox hotelPane = this.createHTEInfoBoard();

        for (Area[] areaList : areas) {
            hotel.areas.addAll(Arrays.asList(areaList));
        }

        return hotelPane;
    }

    private void createNeighbours(Area[][] areas, int hotelWidth, int hotelHeight) {

        // if we want elevator
//        for (int x = 0; x <= hotelWidth; x++) {
//            for (int y = 0; y <= hotelHeight; y++) {
//                Area currentArea = areas[x][y];
//
//                if (x == 0) {
//                    currentArea.addNeighbour(areas[currentArea.getX() + 1][currentArea.getY()], 1);
//                    if (y != hotelHeight) {
//                        currentArea.addNeighbour(areas[currentArea.getX()][currentArea.getY() + 1], 1);
//                    }
//                    if (y != 0) {
//                        currentArea.addNeighbour(areas[currentArea.getX()][currentArea.getY() - 1], 1);
//                    }
//
//                    continue;
//                }
//
//                if (x == hotelWidth) {
//                    currentArea.addNeighbour(areas[currentArea.getX() - 1][currentArea.getY()], 1);
//
//                    if (y != hotelHeight) {
//                        currentArea.addNeighbour(areas[currentArea.getX()][currentArea.getY() + 1], 1);
//                    }
//                    if (y != 0) {
//                        currentArea.addNeighbour(areas[currentArea.getX()][currentArea.getY() - 1], 1);
//                    }
//
//                    continue;
//                }
//
//                currentArea.addNeighbour(areas[currentArea.getX() - 1][currentArea.getY()], 1);
//                currentArea.addNeighbour(areas[currentArea.getX() + 1][currentArea.getY()], 1);
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
                    //area = new GuestRoom(defaultX, defaultY, width, height, 1);
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

    private VBox createHTEInfoBoard() {
        // Adding the HTE information board
        Pane header = new Pane();
        VBox hotelPane = new VBox();

        this.hteInfoBoard = new Label("HTE : " + HteCounter.getHte());
        hteInfoBoard.setStyle("-fx-font-size: 170%");
        hteInfoBoard.setTextFill(Color.BLACK);
        hteInfoBoard.relocate(255, 5);

        header.getChildren().add(hteInfoBoard);
        Rectangle lobbyButton = new Rectangle();
        lobbyButton.setHeight(50);
        lobbyButton.setWidth(50 * (hotelWidth - 1));
        lobbyButton.setFill(Color.TRANSPARENT);
        lobbyButton.setOnMouseClicked(mouseEvent -> {
            this.hotel.stage.setScene(createPauseScreen());
            hotel.timer.stopTimer();
        });
        gridPane.add(lobbyButton, 1, hotelHeight, hotelWidth - 1, 1);
        hotelPane.getChildren().addAll(header, gridPane);

        return hotelPane;
    }

    // Create pause scene
    public Scene createPauseScreen() {
        return new Scene(createPausePane());
    }

    private Pane createPausePane() {
        Pane pausePane = new Pane();
        Button resumeButton = new Button();
        resumeButton.setText("Resume Game");
        resumeButton.setOnMouseClicked(mouseEvent -> {
            this.hotel.stage.setScene(mainScene);
            hotel.timer.resumeTimer();
        });
        resumeButton.relocate(150, 410);
        pausePane.getChildren().add(resumeButton);
        pausePane.setPrefHeight((hotelHeight + 1) * 50);
        pausePane.setPrefWidth((hotelWidth + 1) * 50);
        Label label = new Label();
        label.setMaxWidth(hotelWidth * 50);
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.JUSTIFY);
        String myString = "";
        for (Guest guest : hotel.guestList) {
            myString += "Guest " + guest.getGuestNumber() + " is at " + guest.getArea() + " @ " + guest.getArea().getX() + "/" + guest.getArea().getY() + "\n";
        }
        label.setText(myString);
        pausePane.getChildren().add(label);
        return pausePane;
    }

    public Stage getStage() {
        return this.hotel.stage;
    }

    public void handleStart() throws Exception {
        this.start(this.getStage());
    }

    public void hteLabelUpdate() {
        Platform.runLater(() -> hteInfoBoard.setText("HTE: " + HteCounter.getHte()));
    }

    @Override
    public void updatedHTE(int HTE) {
        this.hteLabelUpdate();
    }
}