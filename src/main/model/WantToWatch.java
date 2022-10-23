package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a WantToWatchWorkRoom having a collection of media which user wants to watch
public class WantToWatch implements Writable {
    List<Media> wantToWatch;
    String name;

    //EFFECTS: Constructs WantToWatchWorkRoom with a name and empty list of media
    public WantToWatch(String name) {
        this.name = name;
        wantToWatch = new ArrayList<Media>();
    }

    public String getName() {
        return name;
    }

    public int numMedias() {
        return wantToWatch.size();
    }

    public List<Media> getWantToWatch() {
        return Collections.unmodifiableList(wantToWatch);
    }

    //EFFECTS: adds given media to WantToWatch, there can be duplicates
    //MODIFIES: this
    public void addMediaWantToWatch(Media media) {
        wantToWatch.add(media);
    }

    //EFFECTS: returns titles of media in WantToWatch
    public List<String> getTitlesWantToWatch() {
        List<String> listTitle = new ArrayList<String>();
        for (Media m : wantToWatch) {
            String title = m.title;
            listTitle.add(m.type + ": " + title);
        }
        return listTitle;
    }

    //REQUIRES: title must correspond to a media in WantToWatch
    //EFFECTS: Removes media with given title from WantToWatch and adds it to given Watched
    //MODIFIES: this and Watched
    public void moveToWatched(String title, Watched watchedList) {
        for (Media m : wantToWatch) {
            if (m.title.equals(title)) {
                wantToWatch.remove(m);
                watchedList.addMedia(m);
                return;
            }
        }

    }

    //Modelled after model/Workroom toJson Method
    //Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("wantToWatch", wantToWatchToJson());
        return json;
    }

    //Modelled after model/Workroom thingiesToJson Method
    //Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    //EFFECTS: returns media in workshop as a JSON array
    private JSONArray wantToWatchToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Media m : wantToWatch) {
            jsonArray.put(m.toJson());
        }
        return jsonArray;
    }
}
