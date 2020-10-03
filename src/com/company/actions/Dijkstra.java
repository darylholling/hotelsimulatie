package com.company.actions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class Dijkstra {
    //list of unvisited nodes
    private ArrayList<Point> unvisitePoints;
    public Dijkstra(){
        unvisitePoints =new ArrayList<>();
    }
    public String findPath(Point start, Point end){
    Point toCheck = start;
    while (!Visit(toCheck, end)){
        toCheck= unvisitePoints.stream().min(Comparator.comparingInt(n->n.distance)).get();
    }
    return makePath(end);

    }
    boolean Visit(Point current, Point end){
        System.out.println("I'm visiting node: "+ current.name);
        System.out.println("Distance to "+current.name+" is: "+current.distance);
        //check if we reached the end
        if (current == end){
            return true;
        }
        //remove current node because we're visiting it.
        this.unvisitePoints.remove(current);
        //for current node, check all neighbours;
        for (Map.Entry<Point, Integer> entry: current.neighbours.entrySet()){
            Point compared = entry.getKey();
            int newDistance = current.distance + entry.getValue();
            if (newDistance < compared.distance){
                compared.distance = newDistance;
                compared.latest = current;
                //check if we have seen the node before, or if we have to add it to our to-visit list
                if(!unvisitePoints.contains(compared)){
                    unvisitePoints.add(compared);
                    System.out.println("Added to unvisited: "+ compared.name);
                }
            }
        }
        return false;
    }
        //make path
    private String makePath(Point end){
        boolean cont = true;
        Point current = end;
        String path = "";

        while (cont){
            path += current.name;
            //check if we reached the end
            if(current.latest != null) {
                current = current.latest;
                path += "-";
            } else {
                cont = false;
            }
        }
        return path;
    }

}
