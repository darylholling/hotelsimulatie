package com.company.models;

import java.io.File;

public class Settings {
    private int HTETime = 1000;
    private int StairsHTE = 1;
    private int CleanHTE = 5;
    private int ElevatorDeathHTE = 1;
    private File layoutFile;
    private File eventsFile;
    private static Settings settings;

    public static synchronized Settings getSettings() {
        return settings;
    }

    public void setSettings(int hteTime, int stairsHTE, int cleanHTE, int elevatorDeathHTE) {
        this.HTETime = hteTime;
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
}
