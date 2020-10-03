package com.company.actions;

import com.company.models.Area;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class Dijkstra {
    //list of unvisited nodes
    private ArrayList<Area> unvisiteAreas;

    public Dijkstra() {
        unvisiteAreas = new ArrayList<>();
    }

    public String findPath(Area start, Area end) {
        Area toCheck = start;
        while (!Visit(toCheck, end)) {
            toCheck = unvisiteAreas.stream().min(Comparator.comparingInt(n -> n.getDistance())).get();
        }

        return makePath(end);
    }

    boolean Visit(Area current, Area end) {
        System.out.println("I'm visiting node: " + "X:" +  current.getX() + " Y:" + current.getY());
        System.out.println("Distance to " + "X:" +  current.getX() + "Y:" + current.getY() +  " is: " + current.getDistance());
        //check if we reached the end
        if (current == end) {
            return true;
        }
        //remove current node because we're visiting it.
        this.unvisiteAreas.remove(current);
        //for current node, check all neighbours;
        for (Map.Entry<Area, Integer> entry : current.getNeighbours().entrySet()) {
            Area compared = entry.getKey();
            int newDistance = current.getDistance() + entry.getValue();
            if (newDistance < compared.getDistance()) {
                compared.setDistance(newDistance);
                compared.setLatest(current);
                //check if we have seen the node before, or if we have to add it to our to-visit list
                if (!unvisiteAreas.contains(compared)) {
                    unvisiteAreas.add(compared);
//                    System.out.println("Added to unvisited: " + current.getClass() + "X:" +  current.getX() + " Y:" + current.getY());
                }
            }
        }
        return false;
    }

    //make path
    private String makePath(Area end) {
        boolean cont = true;
        Area current = end;
        String path = "";

        while (cont) {
            path += ("X" +  current.getX() + "Y" +  current.getY() + "=>");
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
