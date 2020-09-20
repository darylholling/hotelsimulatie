package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;


public class JsonReader{

    public static void main(String[] args) throws IOException {

        AtomicInteger numberOfRows = new AtomicInteger();
        AtomicInteger numberOfColumns = new AtomicInteger();


        Gson gson = new GsonBuilder().create();

//        String fileName = "json/layout.json";
        Path path = new File("json/layout.json").toPath();
        String fileName = "src/com/company/layout.json";
        Path path = new File(fileName).toPath();

        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

            Layout[] layouts = gson.fromJson(reader, Layout[].class);
            Arrays.stream(layouts).forEach(e -> {
                if (e.getPosition().getX() > numberOfRows.get()){
                    numberOfRows.set(e.getPosition().getX());
                }  if (e.getPosition().getY() > numberOfRows.get()){
                    numberOfColumns.set(e.getPosition().getY());
                }
                System.out.println("X = " + e.getPosition().getX() + " Y = " + e.getPosition().getY());
                System.out.println(numberOfRows + "  " + numberOfColumns);
            });
        }
    }
    public void readJson(){

    }
}