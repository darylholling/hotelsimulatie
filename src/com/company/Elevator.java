package com.company;

import java.util.Scanner;
import java.util.ArrayList;

public class Elevator {
    public static void main(String[] args){
        Elevator e = new Elevator();
        e.askPassenger();

    }
    //variables
    Scanner intel = new Scanner(System.in);
    ArrayList<Integer> listOfFloors;
    final int maxPassenger = 20;
    final int maxFloor = 20;
    final int minFloor = 1;
    final int minPassenger = 1;
    int curFloor = 1;
    int desFloor = 1;
    int passFloor = 0;
    int numOfPass = 0;
    boolean isDoorOpen = false;
    int[] destination_lists = new int[maxFloor];


    void goUp(){
        display(curFloor++ + "F | Going up...");
    }
    void goDown(){
        display(curFloor-- + "F | Going down...");
    }
    void delay (int ms){
        try {
            Thread.sleep(ms);
        } catch (Exception e){}
    }
    void display (Object o){
        System.out.println(o);
    }
    void ask(Object o){
        System.out.println(o);
    }

    void askPassenger(){
        isDoorOpen = false;
        display("Elevator opening...");
        delay(1000);
        isDoorOpen = true;
        ask("Current Floor: "+ curFloor + "F. How many passengers: ");
        numOfPass = intel.nextInt();
        if(numOfPass < minPassenger || numOfPass > maxPassenger){
            display("Error. Valid number of passenger is [1-20]");
            askPassenger();
        } else {
            listOfFloors = new ArrayList<>();
            for(int a = 0; a<numOfPass; a++){
                int floor = askPassengerFloor(a);
                if(!listOfFloors.contains(floor)) {listOfFloors.add(floor);}
            }
        }
        display("Elevator closing...");
        delay(1000);
        isDoorOpen = false;
        initialize_elevator();
    }
    int askPassengerFloor(int id){
        boolean isValidEntry = false;
        int floor = 0;
        while(!isValidEntry){
            ask("Passenger #" + (id+1) + " enter your floor: ");
            floor = intel.nextInt();
            if(floor < minFloor || floor > maxFloor){
                display("Error.You have entered out of range floor. Valid [1-20]");
            }
            else if(floor == curFloor) {
                display("you are already on " + curFloor);
            } else {
                destination_lists[floor-1]++;
                isValidEntry = true;
            }
        }
        return floor;
    }

    void initialize_elevator(){
        for (int a = 0;  a < listOfFloors.size(); a++){
            int shortest = findShortest();
            display("Next destination "+ shortest + "F. Passenger amount: "+ destination_lists[shortest-1]);
            delay(1000);
            while (curFloor < shortest){
                goUp();
            }
            while(curFloor > shortest){
                goDown();
            }
            while (destination_lists[shortest-1]> 0){
                display("Unloading passenger ("+ destination_lists[shortest-1]-- + ") at "+ curFloor+"F");
                delay(1000);
            }
        }
        askPassenger();
    }
    int findShortest(){
        int shortest = Math.abs(curFloor-listOfFloors.get(0));
        int id = 0;
        for (int a = 1; a < listOfFloors.size(); a++){
            if(shortest > Math.abs(curFloor-listOfFloors.get(a))){
                shortest = Math.abs(curFloor-listOfFloors.get(a));
                id = a;
            }
        }
        shortest = listOfFloors.get(id);
        listOfFloors.set(id,100);
        return shortest;
    }
}
