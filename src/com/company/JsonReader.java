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


        Gson gson = new GsonBuilder().create();

        Path path = new File("json/layout.json").toPath();

        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

            Layout[] layouts = gson.fromJson(reader, Layout[].class);
            Arrays.stream(layouts).forEach(System.out::println);
        }
    }
    public void readJson(){
        
    }
}