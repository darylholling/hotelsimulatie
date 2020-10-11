package com.company.actions;

import com.company.models.Person;
import com.company.models.areas.Area;

import java.util.*;

public class Dijkstra {
    public ArrayList<Area> unvisitedAreas;

    public Dijkstra() {
        unvisitedAreas = new ArrayList<>();
    }

    public LinkedList<Area> findPath(Person person, Area start, Area end) {
        Area toCheck = start;

        while (!Visit(person, toCheck, end)) {
            try {
                toCheck = unvisitedAreas.stream().min(Comparator.comparingInt(n -> n.getDistanceForPerson(person))).get();
            } catch (NoSuchElementException ex) {
                break;
            }
        }

        return makePath(person, end);
    }

    boolean Visit(Person person, Area current, Area end) {
        if (current == end) {
            return true;
        }

        unvisitedAreas.remove(current);

        for (Map.Entry<Area, Integer> entry : current.getNeighbours().entrySet()) {
            Area compared = entry.getKey();
            int newDistance = current.getDistanceForPerson(person) + entry.getValue();
            if (newDistance <= compared.getDistanceForPerson(person)) {
                compared.setDistanceForPerson(person, newDistance);
                compared.setLatestForPerson(person, current);

                if (!unvisitedAreas.contains(compared)) {
                    unvisitedAreas.add(compared);
                }
            }
        }
        return false;
    }

    //making path to return being made of Areas
    private LinkedList<Area> makePath(Person person, Area end) {
        boolean cont = true;
        Area current = end;
        LinkedList<Area> path = new LinkedList<>();

        while (cont) {
            path.addFirst(current);

            //check if we reached the end
            if (current.getLatestForPerson(person) != null) {
                current = current.getLatestForPerson(person);
            } else {
                cont = false;
            }
        }
        for (Area area : path) {
            area.setLatestForPerson(person, null);
            area.setDistanceForPerson(person, Integer.MAX_VALUE);
        }

        return path;
    }
}
