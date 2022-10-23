package persistence;

import model.Media;
import model.WantToWatch;
import model.Watched;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Class modelled after persistence/JsonReader/
// Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a reader that reads a workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WantToWatch read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    public Watched readWatched() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoomWatched(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses a workroom from JSON object and returns it
    private WantToWatch parseWorkRoom(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        WantToWatch wr = new WantToWatch(name);
        addMedias(wr, jsonObject);
        return wr;
    }

    private Watched parseWorkRoomWatched(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Watched w = new Watched(name);
        addMedias(w, jsonObject);
        return w;
    }


    // MODIFIES: wr
    // EFFECTS: parses medias from JSON object and adds them to given workroom
    private void addMedias(WantToWatch wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("wantToWatch");
        for (Object json : jsonArray) {
            JSONObject nextMedia = (JSONObject) json;
            addMedia(wr, nextMedia);
        }
    }

    private void addMedias(Watched w, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("watched");
        for (Object json : jsonArray) {
            JSONObject nextMedia = (JSONObject) json;
            addMedia(w, nextMedia);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses media from JSON object and adds it to workroom
    private void addMedia(WantToWatch wr, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String type = jsonObject.getString("type");


        Media media = new Media(title, type);
        wr.addMediaWantToWatch(media);
    }

    private void addMedia(Watched w, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String type = jsonObject.getString("type");
        Integer rating = jsonObject.getInt("rating");
        String note = jsonObject.getString("note");
        Media media = new Media(title, type);
        try {
            media.addRating(rating);
        } catch (Exception e) {
            e.printStackTrace();
        }
        media.addNote(note);
        w.addMedia(media);
    }


}