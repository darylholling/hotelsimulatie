package com.company.actions;

import com.company.models.areas.Area;
import com.company.models.areas.Lobby;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;

public class Dijkstra {
    private ArrayList<Area> unvisitedAreas;

    public Dijkstra() {
        unvisitedAreas = new ArrayList<>();
    }

    public LinkedList<Area> findPath(Area start, Area end) {
        Area toCheck = start;

        while (!Visit(toCheck, end)) {
            toCheck = unvisitedAreas.stream().min(Comparator.comparingInt(n -> n.getDistance())).get();
        }

        return makePath(start, end);
    }

    boolean Visit(Area current, Area end) {
        if (current == end) {
            return true;
        }

        unvisitedAreas.remove(current);

        for (Map.Entry<Area, Integer> entry : current.getNeighbours().entrySet()) {
//            System.out.println("get neighbour entryset: "+ current.getNeighbours().entrySet());
            Area compared = entry.getKey();
//            System.out.println("current" + current+ " x: "+current.getX()+ "Y: "+ current.getY());
//            System.out.println("current" + compared+ " x: "+compared.getX()+ "Y: "+ compared.getY());
//            System.out.println("distance to current: "+current.getDistance() + entry.getValue());
            int newDistance = current.getDistance() + entry.getValue();
            if (newDistance <= compared.getDistance()) {
                compared.setDistance(newDistance);
                compared.setLatest(current);

                if (!unvisitedAreas.contains(compared)) {
                    unvisitedAreas.add(compared);
                }
            }
        }

        return false;
    }

    private LinkedList<Area> makePath(Area start, Area end) {
        boolean cont = true;
        Area current = end;
        LinkedList<Area> path = new LinkedList<>();

        while (cont) {
//            System.out.println("current: "+current);
//            if (start != current) {
                path.addFirst(current);
////                System.out.println("kom je hier?");
//            }

            //check if we reached the end
            if (current.getLatest() != null) {
                current = current.getLatest();
//                System.out.println("en hier dan?");
            } else {
                cont = false;
            }
        }

        return path;
    }
}
