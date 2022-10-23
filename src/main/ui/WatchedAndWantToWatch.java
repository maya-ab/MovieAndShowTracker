package ui;

import model.Media;
import model.WantToWatch;
import model.Watched;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Keeps track of watched and wantToWatchLists in app, allows user to save and load them
public class WatchedAndWantToWatch {
    public Watched watchedList;
    public WantToWatch wantToWatch;

    public static final String JSON_STORE = "./data/workroom.json";
    public static final String JSON_STORE_WATCHED = "./data/workroomWatched.json";

    public JsonWriter jsonWriterWantToWatch;
    public JsonReader jsonReaderWantToWatch;

    public JsonReader jsonReaderWatched;
    public JsonWriter jsonWriterWatched;

    //MODIFIES: this
    //EFFECTS: initializes fields
    WatchedAndWantToWatch() {
        wantToWatch = new WantToWatch("My WorkRoom");
        watchedList = new Watched("My WorkRoom");

        jsonWriterWantToWatch = new JsonWriter(JSON_STORE);
        jsonReaderWantToWatch = new JsonReader(JSON_STORE);

        jsonWriterWatched = new JsonWriter(JSON_STORE_WATCHED);
        jsonReaderWatched = new JsonReader(JSON_STORE_WATCHED);
    }

    public Watched getWatchedList() {
        return watchedList;
    }

    public WantToWatch getWantToWatchWorkRoom() {
        return wantToWatch;
    }

    //MODIFIES: this and wantToWatch
    //EFFECTS: adds a new media to WantToWatch
    public void enterWantToWatchMedia(String title, String type) {
        Media enteredMedia = new Media(title, type);
        wantToWatch.addMediaWantToWatch(enteredMedia);
    }

    //MODIFIES: this and Watched
    //EFFECTS: adds a new media to Watched
    public void enterWatchedMedia(String title, String type, Integer rating, String note) {
        Media enteredMedia = new Media(title, type);

        try {
            enteredMedia.addRating(rating);
        } catch (Exception e) {
            e.printStackTrace();
        }

        enteredMedia.addNote(note);

        watchedList.addMedia(enteredMedia);
    }

    //EFFECTS: Shows users titles in WantToWatchWorkShop and asks them if they want to mark one as watched
    public String lookAtTitlesWantToWatch() {
        String infoWantToWatch = wantToWatch.getTitlesWantToWatch().toString();
        return formatList(infoWantToWatch);

    }

    //EFFECTS: Returns string of title, note, type, and rating for each media in watchedList
    public String lookAtWatched() {
        List<String> titlesOfWatched = watchedList.getTitlesWatchedList();
        List<String> watchedInfo = new ArrayList<>();

        for (String s : titlesOfWatched) {
            String watchedDetails = watchedList.getMediaDetails(s).toString();
            watchedInfo.add("-" + watchedDetails);
        }

        String watchedInfoString = watchedInfo.toString();
        return formatList(watchedInfoString);
    }

    //EFFECTS: Returns formatted string
    public String formatList(String s) {
        return s.replace("[", "").replace("]", "");
    }

    //Modelled after ui/WorkroomApp/
    //Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    //EFFECTS: saves the wantToWatchWorkShop to file
    public void saveWorkRoom() {
        try {
            jsonWriterWantToWatch.open();
            jsonWriterWantToWatch.write(wantToWatch);
            jsonWriterWantToWatch.close();

            jsonWriterWatched.open();
            jsonWriterWatched.write(watchedList);
            jsonWriterWatched.close();
            System.out.println("Saved titles");

        } catch (FileNotFoundException e) {
            System.out.println("Can't write file");
        }
    }


    //Modelled after ui/WorkroomApp/
    //Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    //MODIFIES: THIS
    //EFFECTS: loads wantToWatchWorkShop from file
    public void loadWorkRoom() {
        try {

            wantToWatch = jsonReaderWantToWatch.read();

            watchedList = jsonReaderWatched.readWatched();
            System.out.println("Loaded titles ");

        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
    }


}
