package com.company.models.areas;

import java.io.FileNotFoundException;

public class Lobby extends Area {
    public Lobby(int x, int y, int areaWidth, int areaHeight) throws FileNotFoundException {
        super(x, y, areaWidth, areaHeight);
        super.setDefaultImage(this, "lobby.jpg", areaWidth);
    }
}
