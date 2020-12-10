package com.company.models;

import java.io.File;

public class Settings {
    private static Settings settings = null;
    private int HTETime = 250;
    private int StairsHTE = 1;
    private int CleanHTE = 5;
    private int ElevatorDeathHTE = 1;
    private File layoutFile;
    private File eventsFile;
    private int highestHteInJsonFile;

    //get settings
    public static synchronized Settings getSettings() {
        if (settings == null) {
            settings = new Settings();
        }

        return settings;
    }

    //set Settings
    public void setSettings(int HTETime, int stairsHTE, int cleanHTE, int elevatorDeathHTE) {
        this.HTETime = HTETime;
        this.StairsHTE = stairsHTE;
        this.CleanHTE = cleanHTE;
        this.ElevatorDeathHTE = elevatorDeathHTE;
    }

    //get HTE time
    public int getHTETime() {
        return HTETime;
    }

    //get HTE time to take stairs
    public int getStairsHTE() {
        return StairsHTE;
    }

    //get HTE time to clean to room
    public int getCleanHTE() {
        return CleanHTE;
    }

    //get HTE time to die waiting for the elevator
    public int getElevatorDeathHTE() {
        return ElevatorDeathHTE;
    }

    //get layout file
    public File getLayoutFile() {
        return layoutFile;
    }

    //set layout file
    public void setLayoutFile(File layoutFile) {
        this.layoutFile = layoutFile;
    }

    //get event file
    public File getEventsFile() {
        return eventsFile;
    }

    //set event file
    public void setEventsFile(File eventsFile) {
        this.eventsFile = eventsFile;
    }

    //get highest HTE in Json file
    public int getHighestHteInJsonFile() {
        return highestHteInJsonFile;
    }

    //set highest HTE from Json File
    public void setHighestHteInJsonFile(int highestHteInJsonFile) {
        this.highestHteInJsonFile = highestHteInJsonFile;
    }
}