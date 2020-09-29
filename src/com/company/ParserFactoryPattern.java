package com.company;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;

public class ParserFactoryPattern {
    public static void main(String[] args) throws IOException {
        JsonFactory jsonFactory = new JsonFactory();

        //get an object of Circle and call its draw method.
        Parser json1 = jsonFactory.getParser("layout");
        Parser json2 = jsonFactory.getParser("event");
        JsonInfo[] layouts = json1.readJson(new File("src/layout.json"));
        JsonInfo[] events = json2.readJson(new File("src/events1.json"));
        for (JsonInfo e : layouts) {
        }
        for (JsonInfo e : events) {
        }


    }
}

