package com.company.models;

import com.company.listeners.StartListener;
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
    private Settings settings = Settings.getSettings();

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

    public void changeScene(String newScene) {
        Scene scene = mainMenu();

        switch (newScene) {
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
        BorderPane base = createBorderPane();
        VBox baseMenu = this.createVBox();

        if (!this.jsonErrors.isEmpty()) {
            for (String errorName : this.jsonErrors) {
                Label label = new Label("There was an error loading the " + errorName + ".");
                label.setStyle("-fx-padding:10;");
                label.setTextFill(Color.RED);
                label.relocate(5, 0);
                baseMenu.getChildren().add(label);
                this.settings.setEventsFile(null);
                this.settings.setLayoutFile(null);
            }
        }

        Label instructionMenu = new Label("Welcome to the BEST hotel simulation \nGo to settings to adjust the hotel to your liking \n\tThe settings consist of: \n\t How fast time passes in the hotel. \n\t How long it take for certain actions by guests \n\t And how long it takes for guests to die ofcourse");
        instructionMenu.setStyle("-fx-padding:10;");
        instructionMenu.relocate(5, 5);

        Button settingsMenu = new Button("Settings");
        Button loadFilePage = new Button("Start the hotel");

        settingsMenu.setMaxWidth(Double.MAX_VALUE);
        loadFilePage.setMaxWidth(Double.MAX_VALUE);

        GridPane mainMenuButtons = new GridPane();
        mainMenuButtons.setHgap(15);
        mainMenuButtons.setVgap(15);
        mainMenuButtons.setAlignment(Pos.CENTER);
        mainMenuButtons.add(loadFilePage, 0, 0);
        mainMenuButtons.add(settingsMenu, 1, 0);

        loadFilePage.setOnAction((ActionEvent event) -> changeScene("LOADPAGE"));
        settingsMenu.setOnAction((ActionEvent event) -> changeScene("SETTINGS"));

        baseMenu.getChildren().addAll(instructionMenu, mainMenuButtons);
        base.setCenter(baseMenu);

        return base;
    }

    public Scene mainMenu() {
        return new Scene(this.mainMenuContent());
    }

    public Scene settingsForm() {
        BorderPane base = createBorderPane();
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

        Button returnButton = new Button("Return To Menu");
        returnButton.setOnAction((ActionEvent Event) -> changeScene("MAINMENU"));
        returnButton.relocate(0, 5);

        Label title = new Label("Settings");
        title.setStyle("-fx-font-size: 170%");
        title.setTextFill(Color.BLACK);
        title.relocate(255, 5);

        Label introSetttings = new Label("This is where you can edit the settings");
        introSetttings.setAlignment(Pos.CENTER);
        Label ExplainHTE = new Label("HTE is the the unit for time in the hotel");
        Button saveButton = new Button("Save settings");

        Label setHTETimeLabel = new Label("Amount of milliseconds a HTE represents:");
        String setHTETimeText = String.valueOf(this.settings.getHTETime());
        TextField setHTETimeInput = new TextField(setHTETimeText);

        Label setHTEStairsLabel = new Label("Amount of HTE it takes guests to use stairs:");
        String setHTEStairsText = String.valueOf(this.settings.getStairsHTE());
        TextField setHTEStairsInput = new TextField(setHTEStairsText);

        Label setHTECleanLabel = new Label("Amount of HTE it takes to clean a room:");
        String setHTECleanText = String.valueOf(this.settings.getCleanHTE());
        TextField setHTECleanInput = new TextField(setHTECleanText);

        Label setHTEDeathLabel = new Label("Amount of HTE it takes for guests to die from waiting for the elevator:");
        String setHTEDeathText = String.valueOf(this.settings.getElevatorDeathHTE());
        TextField setHTEDeathInput = new TextField(setHTEDeathText);

        header.getChildren().add(title);
        header.getChildren().add(returnButton);

        settingsGridPane.add(introSetttings, 0, 0);
        settingsGridPane.add(setHTETimeLabel, 0, 1);
        settingsGridPane.add(setHTETimeInput, 1, 1);
        settingsGridPane.add(setHTEStairsLabel, 0, 2);
        settingsGridPane.add(setHTEStairsInput, 1, 2);
        settingsGridPane.add(setHTECleanLabel, 0, 3);
        settingsGridPane.add(setHTECleanInput, 1, 3);
        settingsGridPane.add(setHTEDeathLabel, 0, 4);
        settingsGridPane.add(setHTEDeathInput, 1, 4);

        saveButton.setOnAction((ActionEvent event) -> {
            int setHTETime = Integer.parseInt(setHTETimeInput.getText());
            int setHTEStairs = Integer.parseInt(setHTEStairsInput.getText());
            int setHTEClean = Integer.parseInt(setHTECleanInput.getText());
            int setHTEDeath = Integer.parseInt(setHTEDeathInput.getText());

            this.settings.setSettings(setHTETime, setHTEStairs, setHTEClean, setHTEDeath);
        });

        settingsPane.getChildren().addAll(header, settingsGridPane, ExplainHTE, saveButton);
        base.setCenter(settingsPane);

        return new Scene(base);
    }

    public Scene filePage() {
        BorderPane base = createBorderPane();
        VBox filePage = this.createVBox();

        Label filePageTitle = new Label("Please select the files you want to use to run the hotel");
        filePageTitle.setStyle("-fx-padding:10;");
        filePageTitle.relocate(5, 5);

        Label eventStatus = new Label();
        Label layoutStatus = new Label();

        FileChooser eventsChooser = new FileChooser();
        FileChooser layoutChooser = new FileChooser();

        Button eventButton = new Button("Select the event json");
        Button layoutButton = new Button("Select the layout json");
        Button startHotelButton = new Button("Start up hotel");
        Button menuButton = new Button("Back to menu");
        menuButton.relocate(0, 5);

        eventsChooser.setTitle("Choose the event file");
        layoutChooser.setTitle("Choose the layout file");

        eventsChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json file", "*.json"));
        layoutChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json file", "*.json"));

        eventButton.setOnAction(e -> {
            File eventFile = eventsChooser.showOpenDialog(this.stage);
            if (eventFile != null) {
                this.settings.setEventsFile(eventFile);
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
                this.settings.setLayoutFile(layoutFile);
                layoutStatus.setText("Layout file selected: " + layoutFile.getName());
                layoutStatus.setTextFill(Color.BLACK);
            } else {
                layoutStatus.setText("Layout file selection cancelled.");
                layoutStatus.setTextFill(Color.RED);
            }
        });

        if (this.settings.getEventsFile() != null) {
            eventStatus.setText("Event file selected: " + this.settings.getEventsFile().getName());
            eventStatus.setTextFill(Color.BLACK);
        }

        if (this.settings.getLayoutFile() != null) {
            layoutStatus.setText("Layout file selected: " + this.settings.getLayoutFile().getName());
            layoutStatus.setTextFill(Color.BLACK);
        }

        double maxWidth = Double.MAX_VALUE;
        eventButton.setMaxWidth(maxWidth);
        layoutButton.setMaxWidth(maxWidth);

        GridPane fileChooserArea = this.generateGridpane();
        fileChooserArea.add(eventButton, 0, 0);
        fileChooserArea.add(layoutButton, 1, 0);

        GridPane fileChooserAreaButton = this.generateGridpane();
        fileChooserAreaButton.add(startHotelButton, 0, 0);

        GridPane fileChooserAreaText = this.generateGridpane();
        fileChooserAreaText.add(eventStatus, 0, 0);
        fileChooserAreaText.add(layoutStatus, 0, 1);

        menuButton.setOnAction((ActionEvent Event) -> changeScene("MAINMENU"));

        filePage.getChildren().addAll(filePageTitle, fileChooserArea, fileChooserAreaText, fileChooserAreaButton, menuButton);
        base.setCenter(filePage);
        Scene scene = new Scene(base);

        startHotelButton.setOnAction(e -> {
            this.jsonErrors.clear();

            if (this.settings.getEventsFile() != null && this.settings.getLayoutFile() != null) {
                try {
                    this.notifyStart();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            if (this.settings.getEventsFile() == null) {
                eventStatus.setText("Please select a event file!");
                eventStatus.setTextFill(Color.RED);
            }

            if (this.settings.getLayoutFile() == null) {
                layoutStatus.setText("Please select a layout file!");
                layoutStatus.setTextFill(Color.RED);
            }
        });

        return scene;
    }

    private GridPane generateGridpane() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        return gridPane;
    }

    private void notifyStart() throws Exception {
        for (StartListener startListener : startListeners) {
            startListener.handleStart();
        }
    }

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
