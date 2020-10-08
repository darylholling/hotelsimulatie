package com.company.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HteCounterTest {

    ArrayList<HTEListener> HTElisteners;
    HteCounter hteCounter = new HteCounter(HTElisteners);

    @Test
    public void checkIfHteCounterStarts() {
        System.out.println(HteCounter.hte);
       // hteCounter.run();

    }

}