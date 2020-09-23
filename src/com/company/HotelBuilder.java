package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class HotelBuilder extends Application {
    int maxWidth = 0;
    int maxHeight = 0;

    private Parent createContent() throws IOException {

        // size of window
        Pane root = new Pane();
        GridPane gridPane = new GridPane();

        Gson gson = new GsonBuilder().create();
//        Path path = new File("json/2roomlayout.json").toPath();
        Path path = new File("json/layout.json").toPath();

        Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        Layout[] layouts = gson.fromJson(reader, Layout[].class);

        // every object in json file
        for (Layout e : layouts) {
            int layoutHeight = e.getPosition().getY() + (e.getDimensions().getHeight() - 1);
            if (maxHeight < layoutHeight) {
                maxHeight = layoutHeight;
            }
            int layoutWidth = e.getPosition().getX() + (e.getDimensions().getWidth() - 1);
            if (maxWidth < layoutWidth) {
                maxWidth = layoutWidth;
            }
        }

        System.out.println("Max X: " + maxWidth + " & Max Y: " + maxHeight);

        //Building the Area's
        for (int i = 0; i <= maxWidth; i++) {
            for (int j = 0; j <= maxHeight; j++) {
//                Rectangle rectangle = new Rectangle(50, 50);
//                rectangle.setFill(Color.LIGHTBLUE);
//                rectangle.setStroke(Color.BLACK);
//                gridPane.add(rectangle, i, j);

                Hallway hallway = new Hallway(new Position(i, j), new Dimensions(1, 1));
                gridPane.add(hallway, hallway.getPosition().getX(), hallway.getPosition().getY());
            }
        }

        this.createAreas(gridPane, layouts);
//        this.createHallway(gridPane);


        for (Node node : gridPane.getChildren()) {
            System.out.println(node.getClass());
        }

        return gridPane;
    }

    private void createHallway(GridPane gridPane) {
        System.out.println(maxHeight);
        System.out.println(maxWidth);
    }

    private void createAreas(GridPane gridPane, Layout[] layouts) {
        for (Layout layout : layouts) {
            Area area = null;

            switch (layout.getType()) {
                case "room":
                    area = new GuestRoom(layout.getPosition(), layout.getDimensions(), layout.getData().getStars());
                    break;
                case "diner":
//                    area = new Diner(layout.getPosition(), layout.getDimensions(), layout.getData().getStars());
                    break;
                default:
                    System.out.println("invalid type");
            }

            if (area != null) {
                gridPane.add(area, area.getPosition().getX(), area.getPosition().getY());
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }
}