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
    int maxWidth = 0;
    int maxHeight = 0;
    int totalMaxHeight = 0;
    int totalMaxWidth = 0;
    private File layoutFile;

    private Parent createContent() throws IOException {

        // size of window
        Pane root = new Pane();
        GridPane gridPane = new GridPane();

//        set layout file to run Hotelbuilder
//        File layoutFile = new File("json/2roomlayout.json");

        JsonReader jsonReader = new JsonReader();
        Layout[] layouts = jsonReader.readJson(layoutFile);

        //to start from bottom left
//        Scale scale = new Scale();
//        scale.setX(1);
//        scale.setY(-1);
//
//        scale.pivotYProperty().bind(Bindings.createDoubleBinding(() ->
//                        gridPane.getBoundsInLocal().getMinY() + gridPane.getBoundsInLocal().getHeight() /2,
//                    gridPane.boundsInLocalProperty()));
//            gridPane.getTransforms().add(scale);

        // every object in json file
        // Kan dit in JsonReader worden verwerkt?
        for (Layout e : layouts) {
            int layoutHeight = e.getPosition().getY() + (e.getDimensions().getHeight() - 1);
            if (maxHeight < layoutHeight) {
                maxHeight = layoutHeight;
                System.out.println("layoutheight: "+layoutHeight);
            }
            int layoutWidth = e.getPosition().getX() + (e.getDimensions().getWidth() - 1);
            if (maxWidth < layoutWidth) {
                maxWidth = layoutWidth;
                System.out.println("layoutW: "+layoutWidth);
            }
        }

        System.out.println("Max X: " + maxWidth + " & Max Y: " + maxHeight);
        totalMaxHeight = (maxHeight + 1);
        totalMaxWidth = (maxWidth + 2);

        //Building the Area's
        for (int i = 0; i <= totalMaxWidth; i++) {
            for (int j = 0; j <= totalMaxHeight; j++) {
                Area area;

                if (i == 0) {
                    area = new ElevatorShaft(new Position(i, j), new Dimensions(1, 1));
                }
                else if (j == totalMaxHeight && i == 1) {
                    area = new Lobby(new Position(i, j), new Dimensions(maxWidth+1, 1));
                }
                else if (i == totalMaxWidth) {
                    area = new Stairs(new Position(i, j), new Dimensions(1, 1));
                } else {
                    area = new Hallway(new Position(i, j), new Dimensions(1, 1));
                }
                if (area instanceof Lobby){
                    gridPane.add(area, i,j, maxWidth+1, 1);
                }
                else if (!(area instanceof Hallway)) {
                    gridPane.add(area, i, j);
                }
            }
        }

        this.createAreas(gridPane, layouts);

        for (Node child : gridPane.getChildren()) {
            System.out.println(child.getClass());
        }

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

    private void createAreas(GridPane gridPane, Layout[] layouts) throws FileNotFoundException {
        for (Layout layout : layouts) {
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

            if (area != null) {
                Node child = this.getChildAtRowCol(gridPane, area.getPosition().getY()+1, area.getPosition().getX()+1);

                if (child != null) {
                    gridPane.getChildren().remove(child);
                }

                gridPane.add(area, area.getPosition().getX()+1, area.getPosition().getY(), area.getDimensions().width, area.getDimensions().height);
            }
        }
        System.out.println("Width of hotel: "+ totalMaxWidth + " and Height: " +  totalMaxHeight);
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