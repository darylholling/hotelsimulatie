//package com.company.actions;
//
//import com.company.models.areas.Area;
//
//
//import java.util.*;
//
//public class DijkstraTwee {
//    public static Graph calculateShortestPathFromSource(Graph graph, Area start) {
//    Set<Area> visitedAreas = new HashSet<>();
//    Set<Area> unvisitedAreas = new HashSet<>();
//    unvisitedAreas.add(start);
//
//        while (unvisitedAreas.size() != 0) {
//        Area current = getLowestDistanceNode(unvisitedAreas);
//        unvisitedAreas.remove(current);
//        for (Map.Entry< Area, Integer> adjacencyPair:
//                current.getNeighbours().entrySet()) {
//            Area neighbours = adjacencyPair.getKey();
//            Integer edgeWeight = adjacencyPair.getValue();
//            if (!visitedAreas.contains(neighbours)) {
//                calculateMinimumDistance(neighbours, edgeWeight, current);
//                unvisitedAreas.add(neighbours);
//            }
//        }
//        visitedAreas.add(current);
//    }
//    return graph;
//
//    private static Area getLowestDistanceNode(Set<Area> unvisitedAreas) {
//        Area lowestDistanceNode = null;
//        int lowestDistance = Integer.MAX_VALUE;
//        for (Area area: unvisitedAreas) {
//            int nodeDistance = area.getDistance();
//            if (nodeDistance < lowestDistance) {
//                lowestDistance = nodeDistance;
//                lowestDistanceNode = area;
//            }
//        }
//        return lowestDistanceNode;
//    }
//    private static void CalculateMinimumDistance(Area evaluationNode,
//                                                 Integer edgeWeigh, Area start) {
//        Integer sourceDistance = start.getDistance();
//        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
//            evaluationNode.setDistance(sourceDistance + edgeWeigh);
//            LinkedList<Area> shortestPath = new LinkedList<>(start.getShortestPath());
//            shortestPath.add(start);
//            evaluationNode.setShortestPath(shortestPath);
//        }
//    }
//
//}
//
