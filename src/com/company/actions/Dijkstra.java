package com.company.actions;

import com.company.persons.Guest;
import com.company.persons.Person;
import com.company.models.areas.Area;

import java.util.*;

public class Dijkstra {
    public ArrayList<Area> unvisitedAreas;

    public Dijkstra() {
        unvisitedAreas = new ArrayList<>();
//        System.out.println("unvisitedareas" + unvisitedAreas.size());
    }

    public LinkedList<Area> findPath(Person person, Area start, Area end) {
//        System.out.print("from" + start);
//        System.out.println("=> to" + end);
        person.getArea().setDistanceForPerson(person, 0);
        Area toCheck = start;

        while (!Visit(person, toCheck, end)) {
            try {
                toCheck = unvisitedAreas.stream().min(Comparator.comparingInt(n -> n.getDistanceForPerson(person))).get();
            } catch (NoSuchElementException ex) {
                break;
            }
        }

//        System.out.println(makePath(person, end));

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

        if (person instanceof Guest) {
            Guest guest = (Guest) person;
//            System.out.println(guest.getGuestNumber());
        }

        while (cont) {
            path.addFirst(current);

            //check if we reached the end
//            System.out.println(current.getLatestForPerson(person));
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

//        System.out.println(path);
        return path;
    }
}
