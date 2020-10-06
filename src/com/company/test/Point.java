package com.company.test;

import java.util.HashMap;

public class Point {
//    The nodes neighbours with the distance to each one
    public HashMap<Point, Integer> neighbours;

//     Data for pathfinder, keeps the current distance
    public int distance;

//    Remembers the previous node
    public Point latest;

//    Name of the node for debugging information
    public String name;

    public Point(String name){
        this.neighbours = new HashMap<>();
        this.distance = Integer.MAX_VALUE;
        this.latest = null;
        this.name = name;
    }


}
