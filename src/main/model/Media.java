package model;

import expections.OutOfRange;
import org.json.JSONObject;
import persistence.Writable;


//Represents a piece of media with a title, type, rating and note
public class Media implements Writable {
    String title;      // Media title
    String type;       // Movie or Show
    Integer rating;    // Rating for media out of 10
    String note;       // Note you want to leave about media, can be left empty

    //Constructs media with given title and type
    public Media(String title, String type) {
        this.title = title;
        this.type = type;
    }

    public String getName() {
        return title;
    }

    public String getType() {
        return type;
    }

    public Integer getRating() {
        return rating;
    }

    public String getNote() {
        return note;
    }


    //EFFECTS: Adds given rating to media
    //MODIFIES: this
    public void addRating(Integer rating) throws Exception {
        if (rating > 10 || rating < 1) {
            throw new OutOfRange();
        } else {
            this.rating = rating;
        }
    }

    //EFFECTS: Adds given note to media
    //MODIFIES: this
    public void addNote(String note) {
        this.note = note;
    }


    //Modelled after model/Thingy toJson Method
    //Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("type", type);
        json.put("rating", rating);
        json.put("note", note);
        return json;
    }
}
