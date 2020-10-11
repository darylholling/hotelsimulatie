package com.company.actions;

import com.company.models.areas.Area;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;

public class Dijkstra {
    public ArrayList<Area> unvisitedAreas;

    public Dijkstra() {
        unvisitedAreas = new ArrayList<>();
    }

    public LinkedList<Area> findPath(Area start, Area end) {
        Area toCheck = start;

        while (!Visit(toCheck, end)) {
            toCheck = unvisitedAreas.stream().min(Comparator.comparingInt(n -> n.getDistance())).get();
        }

        return makePath(end);
    }

    boolean Visit(Area current, Area end) {
        if (current == end) {
            return true;
        }

        unvisitedAreas.remove(current);

        for (Map.Entry<Area, Integer> entry : current.getNeighbours().entrySet()) {
            Area compared = entry.getKey();
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

    private LinkedList<Area> makePath(Area end) {
        boolean cont = true;
        Area current = end;
        LinkedList<Area> path = new LinkedList<>();

        while (cont) {
            path.addFirst(current);

            //check if we reached the end
            if (current.getLatest() != null) {
                current = current.getLatest();
            } else {
                cont = false;
            }
        }

        for (Area area : path) {
            area.setLatest(null);
            area.setDistance(Integer.MAX_VALUE);
        }

        return path;
    }
}
