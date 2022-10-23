package persistence;


import org.json.JSONObject;

//Interface modelled after persistence/Writable/
//Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public interface Writable {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
