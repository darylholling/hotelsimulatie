package com.company;

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
//        File layoutFile = new File("json/layout.json");

        JsonFactory layoutFactory = new JsonFactory();
        Parser layoutInfo = layoutFactory.getParser(layoutFile.toString());
        JsonInfo[] layouts = layoutInfo.readJson(layoutFile);


        // every object in json file
        // Kan dit in JsonReader worden verwerkt?
        for (JsonInfo e : layouts) {
            int layoutHeight = e.getPosition().getY() + (e.getDimensions().getHeight() - 1);
            if (maxY <= layoutHeight) {
                maxY = layoutHeight;
//                System.out.println("layoutheight: "+layoutHeight);
            }
            int layoutWidth = e.getPosition().getX() + (e.getDimensions().getWidth() - 1);
            if (maxX <= layoutWidth) {
                maxX = layoutWidth;
//                System.out.println("layoutW: "+layoutWidth);
            }
        }

//        System.out.println("Max X: " + maxX + " & Max Y: " + maxY);
        totalMaxY = (maxY + 1);
        totalMaxX = (maxX + 2);

        //Building the Area's
        for (int i = 0; i <= totalMaxX; i++) {
            for (int j = 0; j <= totalMaxY; j++) {
                Area area;

                if (i == 0) {
                    area = new ElevatorShaft(new Position(i, j), new Dimensions(1, 1));
                }
                else if (j == totalMaxY && i == 1) {
                    area = new Lobby(new Position(i, j), new Dimensions(maxX +1, 1));
                }
                else if (i == totalMaxX) {
                    area = new Stairs(new Position(i, j), new Dimensions(1, 1));
                } else {
                    area = new Hallway(new Position(i, j), new Dimensions(1, 1));
                }
                if (area instanceof Lobby){
                    gridPane.add(area, i,j, maxX +1, 1);
                }
                else if (!(area instanceof Hallway)) {
                    gridPane.add(area, i, j);
                }
            }
        }

        this.createAreas(gridPane, layouts);

//        for (Node child : gridPane.getChildren()) {
//            System.out.println(child.getClass());
//
//        }

        return gridPane;
    }

    private Node getChildAtRowCol(GridPane gridPane, int row, int col) {
        for (Node child : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(child) == col && GridPane.getRowIndex(child) == row) {
                return child;
            }
        }
        return null;
    }

    private void createAreas(GridPane gridPane, JsonInfo[] layouts) throws FileNotFoundException {
        for (JsonInfo layout : layouts) {
            Area area = null;

            switch (layout.getType()) {
                case "room":
                    area = new GuestRoom(layout.getPosition(), layout.getDimensions(), layout.getData().getStars());
                    break;
                case "diner":
                    area = new Diner(layout.getPosition(), layout.getDimensions(), layout.getData().getMax());
                    break;
                case "fitness":
                    area = new Fitness(layout.getPosition(), layout.getDimensions());
                    break;
                case "movie":
                    area = new Cinema(layout.getPosition(), layout.getDimensions());
                    break;
                default:
                    System.out.println("invalid type");
            }
            //to start from bottom left
            if (area != null) {
                Node child = this.getChildAtRowCol(gridPane, (totalMaxY - area.getPosition().getY())-1, area.getPosition().getX()+1);

                if (child != null) {
                    gridPane.getChildren().remove(child);
                }
                if (area.getDimensions().getHeight()>1){
                    gridPane.add(area, area.getPosition().getX()+1, (totalMaxY - area.getPosition().getY())-area.getDimensions().getHeight(), area.getDimensions().width, area.getDimensions().height);
                } else {
                    gridPane.add(area, area.getPosition().getX() + 1, (totalMaxY - area.getPosition().getY()) - 1, area.getDimensions().width, area.getDimensions().height);
                }
            }
        }
//        System.out.println("Biggest value of X "+ totalMaxX + " and highest value of Y: " + totalMaxY);
    }

    public void setFiles(File file){
        this.layoutFile = file;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.setResizable(false);
        stage.show();
    }
}