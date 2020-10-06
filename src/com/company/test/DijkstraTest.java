package com.company.test;


import com.company.models.areas.Area;

public class DijkstraTest {
    Area[][] areas;
    int maxNumOfArrayElements = 0;
    int hotelHeight;
    int hotelWidth;


    public DijkstraTest(Area[][] areas, int hotelWidth, int hotelHeight) {
        this.areas = areas;
        this.hotelHeight = hotelHeight;
        this.hotelWidth = hotelWidth;
        maxNumOfArrayElements = Math.max(hotelHeight, hotelWidth);
        //checks content of 2d Array
        if (hotelWidth>hotelHeight){
        for(int i = 0; i <= maxNumOfArrayElements; i++) // column
        {
            for(int j = 0; j <= maxNumOfArrayElements-(maxNumOfArrayElements-(hotelHeight)); j++) // row
            {
                System.out.println("x: "+ i);
                System.out.println(areas[i][j].getX());
                System.out.println("Y: "+ j);
                System.out.println(areas[i][j].getY());
                System.out.println("Y: "+ areas[i][j].getClass());
            }
            System.out.println();
        }
        }
        else if (hotelHeight>hotelWidth){
            for(int i = 0; i < maxNumOfArrayElements; i++) // column
            {
                for(int j = 0; j < maxNumOfArrayElements-(maxNumOfArrayElements-(hotelWidth)); j++) // row
                {
                    System.out.println("x: "+ i);
                    System.out.println(areas[i][j].getX());
                    System.out.println("Y: "+ j);
                    System.out.println(areas[i][j].getY());
                    System.out.println("Y: "+ areas[i][j].getClass());
                }
                System.out.println();
            }
        } else {
            for(int i = 0; i < maxNumOfArrayElements; i++) // column
            {
                for(int j = 0; j < maxNumOfArrayElements; j++) // row
                {
                    System.out.println("x: "+ i);
                    System.out.println(areas[i][j].getX());
                    System.out.println("Y: "+ j);
                    System.out.println(areas[i][j].getY());
                    System.out.println("Y: "+ areas[i][j].getClass());
                }
                System.out.println();
            }
        }
    }



    public static void main(String[] args) {

        Point start = new Point("start");
        Point end = new Point("End");

        Point A = new Point("A");
        Point B = new Point("B");
        Point C = new Point("C");
        Point D = new Point("D");
        Point E = new Point("E");
        Point F = new Point("F");
        Point G = new Point("G");

        start.distance = 0;
        start.neighbours.put(A, 2);
        start.neighbours.put(B, 5);

        A.neighbours.put(C,4);
        A.neighbours.put(D,1);
        B.neighbours.put(D,1);
        B.neighbours.put(E,3);
        B.neighbours.put(F,2);
        C.neighbours.put(E,1);
        D.neighbours.put(C,2);
        E.neighbours.put(G,2);
        F.neighbours.put(end, 8);
        F.neighbours.put(G,3);
        G.neighbours.put(end, 4);

        DijkstraTestAction ds = new DijkstraTestAction();
        System.out.println(ds.findPath(start,end));
        System.out.println("From start to A: "+A.distance);
        System.out.println("From start to B: "+B.distance);
        System.out.println("From start to C: "+C.distance);
        System.out.println("From start to D: "+D.distance);
        System.out.println("From start to E: "+E.distance);
        System.out.println("From start to F: "+F.distance);
        System.out.println("From start to G: "+G.distance);

    }
}
