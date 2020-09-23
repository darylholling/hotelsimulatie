package com.company;

public class Settings {
    private int HTETime;
    private int StairsHTE;
    private int CleanHTE;
    private int ElevatorDeathHTE;

    public void setSettings(int hteTime, int stairsHTE, int cleanHTE, int elevatorDeathHTE){
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
}
