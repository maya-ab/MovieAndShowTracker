package persistence;

import model.Media;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Modelled after test/persistence/JsonTest/
// Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonTest {
    protected void checkMedia(String name, String type, Media media) {
        assertEquals(name, media.getName());
        assertEquals(type, media.getType());
    }

    protected void checkMediaWatched(String name, String type, Integer rating, String note, Media media) {
        assertEquals(name, media.getName());
        assertEquals(type, media.getType());
        assertEquals(rating, media.getRating());
        assertEquals(note, media.getNote());
    }


}



