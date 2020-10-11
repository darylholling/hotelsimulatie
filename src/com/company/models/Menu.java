package com.company.models;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Menu {
    private Stage stage;
    private ArrayList<StartListener> startListeners;
    private ArrayList<String> jsonErrors = new ArrayList<>();

    public Menu(Stage stage, ArrayList<StartListener> startListeners) {
        this.stage = stage;
        this.startListeners = startListeners;
        this.stage.setScene(mainMenu());
        this.stage.setTitle("Hotel Simulation");

        try {
            Image gameIcon = new Image(new FileInputStream("src/com/company/images/HotelIcon.png"));
            this.stage.getIcons().add(gameIcon);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.stage.show();
    }

    //function to change scenes based on stringnames
    public void changeScene(String newScene) {
        Scene scene = mainMenu();
        switch (newScene) {
            case "loadFilePage":
//                scene = startGame();
                break;
            case "SETTINGS":
                scene = settingsForm();
                break;
            case "LOADPAGE":
                scene = filePage();
                break;
        }
        stage.setScene(scene);
        stage.show();
    }

    private VBox createVBox() {
        // Centered box
        VBox vbox = new VBox();
        vbox.setStyle(
                "-fx-background-color: whitesmoke;"
                        + "-fx-border-color: black;"
                        + "-fx-border-width: 3 3 3 3;"
                        + "-fx-spacing: 1;"
                        + "-fx-opacity: 80"
        );

        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);
        vbox.setMaxWidth(400);
        vbox.setMaxHeight(400);

        return vbox;
    }

    public BorderPane mainMenuContent() {
        // Main Pane
        BorderPane base = createBorderPane();

        // Centered box
        VBox baseMenu = this.createVBox();

        // Initialise labels for menu
        if (!this.jsonErrors.isEmpty()) {
            for (String errorName : this.jsonErrors) {
                Label label = new Label("There was an error loading the " + errorName + ".");
                label.setStyle("-fx-padding:10;");
                label.setTextFill(Color.RED);
                label.relocate(5, 0);
                baseMenu.getChildren().add(label);
                Settings.getSettings().setEventsFile(null);
                Settings.getSettings().setLayoutFile(null);
            }
        }

        Label instructionMenu = new Label("Welcome to the BEST hotel simulation \nGo to settings to adjust the hotel to your liking \n\tThe settings consist of: \n\t How fast time passes in the hotel. \n\t How long it take for certain actions by guests \n\t And how long it takes for guests to die ofcourse");
        instructionMenu.setStyle("-fx-padding:10;");
        instructionMenu.relocate(5, 5);

        // Buttons
        Button settingsMenu = new Button("Settings");
        Button loadFilePage = new Button("Start the hotel");

        settingsMenu.setMaxWidth(Double.MAX_VALUE);
        loadFilePage.setMaxWidth(Double.MAX_VALUE);

        // Button positions in main menu
        GridPane mainMenuButtons = new GridPane();

        mainMenuButtons.setHgap(15);
        mainMenuButtons.setVgap(15);
        mainMenuButtons.setAlignment(Pos.CENTER);

        mainMenuButtons.add(loadFilePage, 0, 0);
        mainMenuButtons.add(settingsMenu, 1, 0);

        loadFilePage.setOnAction((ActionEvent event) -> changeScene("LOADPAGE"));

        settingsMenu.setOnAction((ActionEvent event) -> changeScene("SETTINGS"));

        // Add everyting to menupane
        baseMenu.getChildren().addAll(instructionMenu, mainMenuButtons);
        base.setCenter(baseMenu);

        return base;
    }

    //creation of main menu scene
    public Scene mainMenu() {
        return new Scene(this.mainMenuContent());
    }

    // Scene for settings pane
    public Scene settingsForm() {
        BorderPane base = createBorderPane();

        // Creating all Panes
        VBox settingsPane = new VBox();
        Pane header = new Pane();
        GridPane settingsGridPane = new GridPane();

        settingsPane.setAlignment(Pos.CENTER);
        settingsPane.setPadding(new Insets(10));
        settingsPane.setMaxWidth(600);
        settingsPane.setMaxHeight(600);
        settingsPane.setSpacing(10);

        settingsGridPane.setStyle(
                "-fx-background-color: whitesmoke;"
                        + "-fx-border-color: black;"
                        + "-fx-border-width: 3 3 3 3;"
                        + "-fx-spacing: 1;"
                        + "-fx-opacity: 80"
        );
        settingsGridPane.setAlignment(Pos.CENTER);
        settingsGridPane.setHgap(10);
        settingsGridPane.setVgap(10);
        settingsGridPane.setPadding(new Insets(10, 10, 10, 10));

        // Return to menu button
        Button returnButton = new Button("Return To Menu");
        returnButton.setOnAction((ActionEvent Event) -> changeScene("MAINMENU"));
        returnButton.relocate(0, 5);

        // Title settings
        Label title = new Label("Settings");
        title.setStyle("-fx-font-size: 170%");
        title.setTextFill(Color.BLACK);
        title.relocate(255, 5);

        // Settings
        Label introSetttings = new Label("This is where you can edit the settings");
        introSetttings.setAlignment(Pos.CENTER);
        Label ExplainHTE = new Label("HTE is the the unit for time in the hotel");
        Button saveButton = new Button("Save settings");

        // Settings HTE
        Label setHTETimeLabel = new Label("Amount of milliseconds a HTE represents:");
        String setHTETimeText = String.valueOf(Settings.getSettings().getHTETime());
        TextField setHTETimeInput = new TextField(setHTETimeText);

        // Settings HTE Stairs
        Label setHTEStairsLabel = new Label("Amount of HTE it takes guests to use stairs:");
        String setHTEStairsText = String.valueOf(Settings.getSettings().getStairsHTE());
        TextField setHTEStairsInput = new TextField(setHTEStairsText);

        // Settings HTE Clean
        Label setHTECleanLabel = new Label("Amount of HTE it takes to clean a room:");
        String setHTECleanText = String.valueOf(Settings.getSettings().getCleanHTE());
        TextField setHTECleanInput = new TextField(setHTECleanText);

        // Settings HTE Death
        Label setHTEDeathLabel = new Label("Amount of HTE it takes for guests to die from waiting for the elevator:");
        String setHTEDeathText = String.valueOf(Settings.getSettings().getElevatorDeathHTE());
        TextField setHTEDeathInput = new TextField(setHTEDeathText);

        // Add everything to header
        header.getChildren().add(title);
        header.getChildren().add(returnButton);

        // Add everything to settings
        settingsGridPane.add(introSetttings, 0, 0);
        settingsGridPane.add(setHTETimeLabel, 0, 1);
        settingsGridPane.add(setHTETimeInput, 1, 1);
        settingsGridPane.add(setHTEStairsLabel, 0, 2);
        settingsGridPane.add(setHTEStairsInput, 1, 2);
        settingsGridPane.add(setHTECleanLabel, 0, 3);
        settingsGridPane.add(setHTECleanInput, 1, 3);
        settingsGridPane.add(setHTEDeathLabel, 0, 4);
        settingsGridPane.add(setHTEDeathInput, 1, 4);

        // actions for save button
        saveButton.setOnAction((ActionEvent event) -> {
            int setHTETime = Integer.parseInt(setHTETimeInput.getText());
            int setHTEStairs = Integer.parseInt(setHTEStairsInput.getText());
            int setHTEClean = Integer.parseInt(setHTECleanInput.getText());
            int setHTEDeath = Integer.parseInt(setHTEDeathInput.getText());

            Settings.getSettings().setSettings(setHTETime, setHTEStairs, setHTEClean, setHTEDeath);
        });

        // Panes adding to hsPane
        settingsPane.getChildren().addAll(header, settingsGridPane, ExplainHTE, saveButton);
        base.setCenter(settingsPane);

        // Primary scene show
        return new Scene(base);
    }

    //creation of scene page for selecting files
    public Scene filePage() {
        // Main Pane
        BorderPane base = createBorderPane();

        VBox filePage = this.createVBox();

        // Initialise labels for menu
        Label filePageTitle = new Label("Please select the files you want to use to run the hotel");
        filePageTitle.setStyle("-fx-padding:10;");
        filePageTitle.relocate(5, 5);

        // text about files
        Label eventStatus = new Label();
        Label layoutStatus = new Label();

        // filesChoosers
        FileChooser eventsChooser = new FileChooser();
        FileChooser layoutChooser = new FileChooser();

        // Buttons
        Button eventButton = new Button("Select the event json");
        Button layoutButton = new Button("Select the layout json");
        Button startHotelButton = new Button("Start up hotel");
        startHotelButton.relocate(0, 5);

        // Name Window
        eventsChooser.setTitle("Choose the event file");
        layoutChooser.setTitle("Choose the layout file");

        // Default file names and extentions
        eventsChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json file", "*.json"));
        layoutChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json file", "*.json"));

        //open file chooser with buttons
        eventButton.setOnAction(e -> {
            File eventFile = eventsChooser.showOpenDialog(this.stage);
            if (eventFile != null) {
                Settings.getSettings().setEventsFile(eventFile);
                eventStatus.setText("Event file selected: " + eventFile.getName());
                eventStatus.setTextFill(Color.BLACK);
            } else {
                eventStatus.setText("Event file selection cancelled.");
                eventStatus.setTextFill(Color.RED);
            }
        });
        layoutButton.setOnAction(e -> {
            File layoutFile = layoutChooser.showOpenDialog(this.stage);
            if (layoutFile != null) {
                Settings.getSettings().setLayoutFile(layoutFile);
//                settings.setLayoutFile(layoutFile);
                layoutStatus.setText("Layout file selected: " + layoutFile.getName());
                layoutStatus.setTextFill(Color.BLACK);
            } else {
                layoutStatus.setText("Layout file selection cancelled.");
                layoutStatus.setTextFill(Color.RED);
            }
        });

        double maxWidth = Double.MAX_VALUE;
        eventButton.setMaxWidth(maxWidth);
        layoutButton.setMaxWidth(maxWidth);

        // Button positions in main menu
        GridPane fileChooserArea = new GridPane();

        fileChooserArea.setHgap(15);
        fileChooserArea.setVgap(15);
        fileChooserArea.setAlignment(Pos.CENTER);

        fileChooserArea.add(eventButton, 0, 0);
        fileChooserArea.add(layoutButton, 1, 0);
        fileChooserArea.add(eventStatus, 0, 1);
        fileChooserArea.add(layoutStatus, 0, 2);
        fileChooserArea.add(startHotelButton, 0, 3);

        // Add everyting to menupane
        filePage.getChildren().addAll(filePageTitle, fileChooserArea);
        base.setCenter(filePage);
        Scene scene = new Scene(base);

        startHotelButton.setOnAction(e -> {
            this.jsonErrors.clear();
            try {
                this.notifyStart();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        return scene;
    }

    //sending notification to all startlisteners that the application has started.
    private void notifyStart() throws Exception {
        for (StartListener startListener : startListeners) {
            startListener.handleStart();
        }
    }

    //pane with background for visual improvements.
    public BorderPane createBorderPane() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(600, 600);
        borderPane.setStyle(
                "-fx-background-image:url(/com/company/images/background.jpg);"
                        + "-fx-background-size: cover, auto;"
        );

        return borderPane;
    }

    public void addJsonError(String string) {
        this.jsonErrors.add(string);
    }
}
