package com.company.models;

import java.io.File;

public class Settings {
    private static Settings settings = null;
    private int HTETime = 100;
    private int StairsHTE = 1;
    private int CleanHTE = 5;
    private int ElevatorDeathHTE = 1;
    private File layoutFile;
    private File eventsFile;
    private int highestHteInJsonFile;

    public static synchronized Settings getSettings() {
        if (settings == null) {
            settings = new Settings();
        }

        return settings;
    }

    public void setSettings(int HTETime, int stairsHTE, int cleanHTE, int elevatorDeathHTE) {
        this.HTETime = HTETime;
        this.StairsHTE = stairsHTE;
        this.CleanHTE = cleanHTE;
        this.ElevatorDeathHTE = elevatorDeathHTE;
    }

    public int getHTETime() {
        return HTETime;
    }

    public int getStairsHTE() {
        return StairsHTE;
    }

    public int getCleanHTE() {
        return CleanHTE;
    }

    public int getElevatorDeathHTE() {
        return ElevatorDeathHTE;
    }

    public File getLayoutFile() {
        return layoutFile;
    }

    public void setLayoutFile(File layoutFile) {
        this.layoutFile = layoutFile;
    }

    public File getEventsFile() {
        return eventsFile;
    }

    public void setEventsFile(File eventsFile) {
        this.eventsFile = eventsFile;
    }

    public int getHighestHteInJsonFile() {
        return highestHteInJsonFile;
    }

    public void setHighestHteInJsonFile(int highestHteInJsonFile) {
        this.highestHteInJsonFile = highestHteInJsonFile;
    }
}