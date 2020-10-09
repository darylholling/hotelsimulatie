package com.company.actions;

import com.company.models.Guest;
import com.company.models.HteCounter;
import com.company.models.areas.Area;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;

public class Dijkstra {
    public Area current;
    //list of unvisited nodes
    private ArrayList<Area> unvisitedAreas;
    public LinkedList<Area> finalPath;

    public Dijkstra() {
        unvisitedAreas = new ArrayList<>();
    }

    public String findPath(Guest guest, Area start, Area end) {
        Area toCheck = start;

        while (!Visit(toCheck, end)) {
            toCheck = unvisitedAreas.stream().min(Comparator.comparingInt(n -> n.getDistance())).get();
        }

        return makePath(guest, end);
    }

    boolean Visit(Area current, Area end) {
        //System.out.println("I'm trying node: " + "X:" +  current.getX() + " Y:" + current.getY());
        //System.out.println("Distance to " + "X:" +  current.getX() + "Y:" + current.getY() +  " is: " + current.getDistance());
        //check if we reached the end
        if (current == end) {
            return true;
        }
        //System.out.println(unvisitedAreas);
        //remove current node because we're visiting it.
        unvisitedAreas.remove(current);
        //System.out.println(current.getNeighbours());
        //for current node, check all neighbours;
        for (Map.Entry<Area, Integer> entry : current.getNeighbours().entrySet()) {
            Area compared = entry.getKey();
            int newDistance = current.getDistance() + entry.getValue();
            if (newDistance <= compared.getDistance()) {
                compared.setDistance(newDistance);
                compared.setLatest(current);
                //check if we have seen the node before, or if we have to add it to our to-visit list
                if (!unvisitedAreas.contains(compared)) {
                    unvisitedAreas.add(compared);
//                    System.out.println("Added to unvisited: " + current.getClass() + "X:" +  current.getX() + " Y:" + current.getY());
                }
            }
        }
        return false;
    }

    //make path
    // @todo reverse this
    private String makePath(Guest guest, Area end) {
        boolean cont = true;
        String path = "";
        current = end;

        while (cont) {
            path += ("X" +  current.getX() + "Y" +  current.getY() + "=>");

            Area finalCurrent = current;
            Platform.runLater(() ->finalPath.add(finalCurrent));

            //check if we reached the end
            if (current.getLatest() != null) {
                current = current.getLatest();
                path += "-";
            } else {
                cont = false;
            }
        }
        return path;
    }
}
