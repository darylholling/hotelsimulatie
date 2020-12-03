package com.company.actions;

import com.company.persons.Guest;
import com.company.models.Hotel;
import com.company.models.HteCounter;
import com.company.models.Settings;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

public class HotelController {
    private Hotel hotel;
    private VBox hotelPane;
    private Label HTEInfoBoard;
    private Label highestHTE;
    private Scene mainScene;


    public HotelController(Hotel hotel) {
        this.hotel = hotel;
    }

    public void createContent() {
        mainScene = new Scene(createMainPane());
        hotel.setScene(mainScene);
    }

    //create textual info presenting HTE time
    private VBox createMainPane() {
        Pane header = new Pane();
        hotelPane = new VBox();

        HTEInfoBoard = new Label("HTE : " + HteCounter.getHTE());
        HTEInfoBoard.setStyle("-fx-font-size: 170%");
        HTEInfoBoard.setTextFill(Color.BLACK);
        HTEInfoBoard.relocate(280, 2);

        highestHTE = new Label("Final event starts at HTE: " + Settings.getSettings().getHighestHteInJsonFile());
        highestHTE.setStyle("-fx-font-size: 170%");
        highestHTE.setTextFill(Color.BLACK);
        highestHTE.relocate(5, 2);

        header.getChildren().addAll(highestHTE, HTEInfoBoard);
        Rectangle lobbyButton = new Rectangle();
        lobbyButton.setHeight(50);
        lobbyButton.setWidth(50 * (hotel.hotelWidth - 1));
        lobbyButton.setFill(Color.TRANSPARENT);
        lobbyButton.toFront();
        lobbyButton.setOnMouseClicked(mouseEvent -> {
            this.hotel.stage.setScene(createPausePane());
            hotel.timer.stopTimer();
        });
        hotel.mainPane.add(lobbyButton, 1, hotel.hotelHeight, hotel.hotelWidth - 1, 1);
        hotelPane.getChildren().addAll(header, hotel.mainPane);

        return hotelPane;
    }

    //creating pane to display guestlist when clicking the lobby
    private Scene createPausePane() {
        Pane pausePane = new Pane();
        Button resumeButton = new Button();
        resumeButton.setText("Resume Game");
        resumeButton.setOnMouseClicked(mouseEvent -> {
            this.hotel.stage.setScene(mainScene);
            hotel.timer.resumeTimer();
        });
        resumeButton.relocate(150, 410);
        pausePane.getChildren().add(resumeButton);
        pausePane.setPrefHeight((hotel.hotelHeight + 1) * 50);
        pausePane.setPrefWidth((hotel.hotelWidth + 1) * 50);
        Label label = new Label();
        label.setMaxWidth(hotel.hotelWidth * 50);
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.JUSTIFY);
        String myString = "";
        for (Guest guest : hotel.activeGuestList) {
            myString += "Guest " + guest.getGuestNumber() + " is at " + guest.getArea().getClass().getSimpleName() + " @ X: " + guest.getArea().getX() + " ,Y: " + guest.getArea().getY() + "\n";
        }
        label.setText(myString);
        pausePane.getChildren().add(label);
        return new Scene(pausePane);
    }

    //updating HTElabel once received from listener
    public void HTELabelUpdate() {
        Platform.runLater(() -> HTEInfoBoard.setText("HTE: " + HteCounter.getHTE()));
        if (highestHTE != null) {
            Platform.runLater(() -> highestHTE.setText("Final event starts at HTE: " + Settings.getSettings().getHighestHteInJsonFile()));
        }
    }
}
