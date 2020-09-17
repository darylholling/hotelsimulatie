import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonToken;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


public class JsonReader{

    public static void main(String[] args) throws IOException {

        Gson gson = new GsonBuilder().create();

        String fileName = "src/layout.json";
        Path path = new File(fileName).toPath();

        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

            Layout[] layouts = gson.fromJson(reader, Layout[].class);
            Arrays.stream(layouts).forEach(e -> {
                System.out.println(e);
            });
        }
    }
}