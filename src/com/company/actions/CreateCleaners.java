package com.company.actions;

import com.company.models.Area;
import com.company.models.Cleaner;
import com.company.models.StartListener;

import java.util.ArrayList;

public class CreateCleaners implements StartListener {
    int cleanerCount = 2;
    private ArrayList<Cleaner> cleaners = new ArrayList<>();

    public CreateCleaners(Area[][] areas) {
        System.out.println(areas.length);
    }

    public void create() {
        for (int i = 0; i < cleanerCount; i++) {
//            Cleaner cleaner = new Cleaner(area);

//            this.cleaners.add(cleaner);
        }
    }

    @Override
    public void handleStart() throws Exception {
        this.create();
    }

    public ArrayList<Cleaner> getCleaners() {
        return cleaners;
    }
}
