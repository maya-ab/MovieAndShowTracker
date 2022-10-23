package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Represents a list of media user has watched
public class Watched implements Writable {

    List<Media> watchedList;
    String name;

    //Constructs a new empty Watched
    public Watched(String name) {
        watchedList = new ArrayList<Media>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int numMedias() {
        return watchedList.size();
    }

    public List<Media> getWatched() {
        return Collections.unmodifiableList(watchedList);
    }

    //EFFECTS: adds given media to Watched, there can be duplicates
    //MODIFIES: this
    public void addMedia(Media media) {
        watchedList.add(media);
    }

    //EFFECTS: returns all titles in watchedList
    public List<String> getTitlesWatchedList() {
        List<String> listTitle = new ArrayList<String>();
        for (Media m : watchedList) {
            String title = m.title;
            listTitle.add(title);
        }
        return listTitle;
    }

    //REQUIRES: Title must correspond to a media in Watched
    //EFFECTS: returns details of media with given title, including it's rating, type, and note
    public List<String> getMediaDetails(String title) {
        List<String> mediaDetails = new ArrayList<String>();
        for (Media m : watchedList) {
            if (m.title.equals(title)) {
                mediaDetails.add(m.title);
                mediaDetails.add(m.type);
                mediaDetails.add(Integer.toString(m.rating));
                mediaDetails.add(m.note);
            }
        }
        return mediaDetails;
    }

    //REQUIRES: Title correspond to a media in Watched, rating must be a whole number in interval [0, 10]
    //EFFECTS: updates media with given title with the provided rating and note
    //MODIFIES: this and Media with given title
    public void addRatingAndNote(String title, Integer rating, String note) {
        for (Media m : watchedList) {
            if (m.title.equals(title)) {
                try {
                    m.addRating(rating);
                } catch (Exception e) {
                    System.out.println("Rating was out of bounds");
                }
                m.addNote(note);
                return;
            }
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("watched", watchedToJason());
        return json;
    }

    private JSONArray watchedToJason() {
        JSONArray jsonArray = new JSONArray();
        for (Media m : watchedList) {
            jsonArray.put(m.toJson());
        }
        return jsonArray;
    }
}
