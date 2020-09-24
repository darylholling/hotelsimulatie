package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class HotelBuilder extends Application {
    int maxWidth = 0;
    int maxHeight = 0;
    private Parent createContent() {

        // size of window
        Pane root = new Pane();
        GridPane gridPane = new GridPane();

        //import json
//        AtomicInteger numberOfRows = new AtomicInteger();
//        AtomicInteger numberOfColumns = new AtomicInteger();

        Gson gson = new GsonBuilder().create();
        Path path = new File("json/layout.json").toPath();


        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            Layout[] layouts = gson.fromJson(reader, Layout[].class);


            // every object in json file
            for(Layout e:layouts) {

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


        } catch (IOException e) {
            e.printStackTrace();
        }

        //Building the Area's
        int totalMaxHeight= maxHeight + 1; //total height incl lobby
        int totalMaxWidth = maxWidth + 2; // total width incl elevator + stairs
        for(int i = 0; i < totalMaxWidth; i++){
            for(int j = 0; j < totalMaxHeight; j++){
                Rectangle area = new Rectangle(50, 50);
                area.setFill(Color.LIGHTBLUE);
                area.setStroke(Color.BLACK);
                gridPane.add(area, i,j);
            }
        }

        return gridPane;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    private class Area extends StackPane {
        public Area() {
            Rectangle border = new Rectangle(50, 50);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            setAlignment(Pos.CENTER);
            getChildren().addAll(border);
        }
    }

    public static void main(String[] args) {
        HotelBuilder hotelBuilder = new HotelBuilder();
        hotelBuilder.createContent();

        launch(args);
    }
}
