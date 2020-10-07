package com.company.models.areas;

import java.io.FileNotFoundException;

public class Fitness extends Area {

      public Fitness (int x, int y, int areaWidth, int areaHeight) throws FileNotFoundException {
          super(x, y, areaWidth, areaHeight);
          super.setDefaultImage(this, "fitness.jpg", areaWidth);
    }
}