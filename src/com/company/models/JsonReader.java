//package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


//public class JsonReader {
//
//    public JsonInfo[] readJson(File filename) throws IOException {
//        Gson gson = new GsonBuilder().create();
//        Path path = new File(String.valueOf(filename)).toPath();
//        Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
//        return gson.fromJson(reader, JsonInfo[].class);
//    }
//    public Event[] readEventJson(String filename) throws IOException {
//        Gson gson = new GsonBuilder().create();
//        Path path = new File(filename).toPath();
//        Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
//        return gson.fromJson(reader, Event[].class);
//    }
//}