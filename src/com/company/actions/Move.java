package com.company.actions;

import com.company.models.Guest;
import com.company.models.Hotel;
import com.company.models.areas.Area;
import javafx.application.Platform;

import java.util.LinkedList;

public class Move {
    private Guest guest;
    private Area startArea;
    private Area endArea;
    public void move(Guest guest, LinkedList <Area> finalPath){
        this.guest = guest;
        startArea = this.guest.getArea();
        endArea = this.guest.getGuestRoom();
        for (int i = finalPath.size(); i >= 0 ; i--){
            System.out.println(finalPath.size());
            Dijkstra ds = new Dijkstra();
            startArea.setDistance(0);
            ds.findPath(guest, startArea, endArea);
            guest.setArea(ds.current);
            Platform.runLater(() -> guest.setGuestImage());
        }

    }
}
