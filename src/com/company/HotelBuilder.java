package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private Parent createContent() {

        // size of window
        Pane root = new Pane();
        root.setPrefSize(600, 600);

        //import json
        AtomicInteger numberOfRows = new AtomicInteger();
        AtomicInteger numberOfColumns = new AtomicInteger();

        Gson gson = new GsonBuilder().create();
        Path path = new File("json/layout.json").toPath();


        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            Layout[] layouts = gson.fromJson(reader, Layout[].class);

            // every object in json file
            Arrays.stream(layouts).forEach(e -> {


                System.out.println(e.getPosition());
                System.out.println(e.getDimensions());

                if (e.getPosition().getX() > numberOfRows.get()) {
                    numberOfRows.set(e.getPosition().getX());
                }
                if (e.getPosition().getY() > numberOfRows.get()) {
                    numberOfColumns.set(e.getPosition().getY());
                }
                System.out.println("X = " + e.getPosition().getX() + " Y = " + e.getPosition().getY());
                System.out.println("rows = " + (1 + numberOfRows.intValue()) + "  columns = " + (1 + numberOfColumns.intValue()));


            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Building the Area's
        for(int i = 0; i < numberOfRows.intValue(); i++){
            for(int j = 0; j < numberOfColumns.intValue(); j++){
                Rectangle area = new Rectangle(10, 10);
                area.setFill(null);
                area.setStroke(Color.BLACK);
                area.setTranslateX(10);
                area.setTranslateY(10);

                root.getChildren().addAll(area);
            }
        }

        return root;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    private class Area extends StackPane {
        public Area() {
            Rectangle border = new Rectangle(10, 10);
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
