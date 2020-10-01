package com.company.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class LayoutParser implements Parser {
    @Override
    public JsonInfo[] readJson(File fileName) throws IOException {
        Gson gson = new GsonBuilder().create();
        Path path = new File(String.valueOf(fileName)).toPath();
        Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        return gson.fromJson(reader, JsonInfo[].class);
    }

}
