package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Menu extends Application {
    private Stage primaryStage;

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setScene(mainMenu());
        this.primaryStage.setTitle("Hotel Simulation");
        this.primaryStage.show();
        Image gameIcon = new Image("com/company/images/HotelIcon.png");
        primaryStage.getIcons().add(gameIcon);

    }

    public void changeScene(String newScene) {
        Scene scene = mainMenu();
        switch (newScene) {
            case "STARTHOTEL":
//                scene = startGame();
                break;
            case "SETTINGS":
                scene = settings();
                break;
        }
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Scene mainMenu() {
        // Main Pane
        BorderPane base = base();

        // Centered box
        VBox baseMenu = new VBox();
        baseMenu.setStyle(
//                "-fx-background-color: whitesmoke;"
                "-fx-background-image:url(/images/background.jpg);"
                +"-fx-border-color: black;"
                +"-fx-border-width: 3 3 3 3;"
                +"-fx-spacing: 1;"
                +"-fx-opacity: 80"
        );

        baseMenu.setPadding(new Insets(10));
        baseMenu.setAlignment(Pos.CENTER);
        baseMenu.setMaxWidth(400);
        baseMenu.setMaxHeight(400);

        // Initialise labels for menu
        Label instructionMenu = new Label("Welcome to the BEST hotel simulation \nGo to settings to adjust the hotel to your liking \n\tThe settings consist of: \n\t How fast time passes in the hotel. \n\t How long it take for certain actions by guests \n\t And how long it takes for guests to die ofcours");
        instructionMenu.setStyle("-fx-padding:10;");
        instructionMenu.relocate(5, 5);

        // Buttons
        Button settingsMenu = new Button("Settings");
        Button startHotel = new Button("Start the hotel");

        settingsMenu.setMaxWidth(Double.MAX_VALUE);
        startHotel.setMaxWidth(Double.MAX_VALUE);

        // Button positions in main menu
        GridPane mainMenuButtons = new GridPane();

        mainMenuButtons.setHgap(15);
        mainMenuButtons.setVgap(15);
        mainMenuButtons.setAlignment(Pos.CENTER);

        mainMenuButtons.add(startHotel, 0, 0);
        mainMenuButtons.add(settingsMenu, 1, 0);


        startHotel.setOnAction((ActionEvent event) -> {
            changeScene("STARTHOTEL");
        });

        settingsMenu.setOnAction((ActionEvent event) -> {
            changeScene("SETTINGS");
        });

        // Add everyting to menupane
        baseMenu.getChildren().addAll(instructionMenu, mainMenuButtons);
        base.setCenter(baseMenu);

        return new Scene(base);
    }

    public Scene settings() {
        BorderPane base = base();

        // Creating all Panes
        VBox settingsPane = new VBox();
        Pane header = new Pane();
        GridPane settings = new GridPane();

        settingsPane.setAlignment(Pos.CENTER);
        settingsPane.setPadding(new Insets(10));
        settingsPane.setMaxWidth(600);
        settingsPane.setMaxHeight(600);
        settingsPane.setSpacing(10);

        settings.setStyle(
            "-fx-background-color: whitesmoke;"
            +"-fx-border-color: black;"
            +"-fx-border-width: 3 3 3 3;"
            +"-fx-spacing: 1;"
            +"-fx-opacity: 80"
        );
        settings.setAlignment(Pos.CENTER);
        settings.setHgap(10);
        settings.setVgap(10);
        settings.setPadding(new Insets(10, 10, 10, 10));

        // Return to menu button
        Button returnButton = new Button("Return To Menu");
        returnButton.setOnAction((ActionEvent Event) -> changeScene("MAINMENU"));
        returnButton.relocate(0, 5);

        // Title settings
        Label title = new Label("Settings");
        title.setStyle("-fx-font-size: 170%");
        title.setTextFill(Color.BLACK);
        title.relocate(200, 5);

        // Settings
        Label introSetttings = new Label("This is where you can edit the settings");
        Label ExplainHTE = new Label("HTE is the the unit for time in the hotel");
        Button saveButton = new Button("Save settings");

        // Settings HTE
        Label setHTELabel = new Label("Amount of milliseconds a HTE represents:");
        TextField setHTEInput = new TextField("Enter the amount of ms");

        // Settings HTE Stairs
        Label setHTEStairsLabel = new Label("Amount of HTE it takes gusets to use stairs:");
        TextField setHTEStairsInput = new TextField("Enter the amount of HTE");

        // Add everything to header
        header.getChildren().add(title);
        header.getChildren().add(returnButton);

        // Add everything to settings
        settings.add(introSetttings,0,0);
        settings.add(setHTELabel,0,1);
        settings.add(setHTEInput,1,1);
        settings.add(setHTEStairsLabel,0,2);
        settings.add(setHTEStairsInput,1,2);


        // actions for save button
        saveButton.setOnAction((ActionEvent event) -> {
            System.out.println("Saving data...");
            //Todo save all data
        });

        // Panes adding to hsPane
        settingsPane.getChildren().addAll(header,settings,ExplainHTE,saveButton);
        base.setCenter(settingsPane);

        // Primary scene show
        return new Scene(base);
    }

    public BorderPane base() {
        BorderPane base = new BorderPane();
        base.setPrefSize(1000, 1000);
        base.setStyle(
                "-fx-background-image:url(images/background.jpg);"
                +"-fx-background-size: cover, auto;"
        );
        return base;
    }
}
