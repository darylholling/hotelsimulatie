package com.company.area;

import com.company.person.Person;
import com.company.hotel.Position;

import java.io.File;

public class ElevatorShaft extends Area {

    public ElevatorShaft(Position position, Person persons, File imageFile, Dimensions dimensions, String areaType) {
        super(position, persons, imageFile, dimensions, areaType);
    }
}