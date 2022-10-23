package persistence;

import model.Media;
import model.WantToWatch;
import model.Watched;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Modelled after test/persistence/JsonWriterTest
// Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            WantToWatch wr = new WantToWatch("My work room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }

        try {
            Watched w = new Watched("My work room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            WantToWatch wr = new WantToWatch("My work room");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            wr = reader.read();

            assertEquals("My work room", wr.getName());
            assertEquals(0, wr.numMedias());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            WantToWatch wr = new WantToWatch("My work room");

            wr.addMediaWantToWatch(new Media("movie1", "movie"));
            wr.addMediaWantToWatch(new Media("show1", "show"));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            wr = reader.read();
            assertEquals("My work room", wr.getName());

            List<Media> wantToWatch = wr.getWantToWatch();
            assertEquals(2, wantToWatch.size());

            checkMedia("movie1", "movie", wantToWatch.get(0));
            checkMedia("show1", "show", wantToWatch.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkRoomWatched() {
        try {
            Watched w = new Watched("My work room");
            Media m1 = new Media("movie1", "movie");
            try {
                m1.addRating(9);
            } catch (Exception e) {
                e.printStackTrace();
            }
            m1.addNote("Good");

            Media m2 = new Media("show1", "show");
            try {
                m2.addRating(8);
            } catch (Exception e) {
                e.printStackTrace();
            }
            m2.addNote("Ok");

            w.addMedia(m1);
            w.addMedia(m2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroomWatched.json");
            writer.open();
            writer.write(w);
            writer.close();

            JsonReader readerWatched = new JsonReader("./data/testWriterGeneralWorkroomWatched.json");
            w = readerWatched.readWatched();
            assertEquals("My work room", w.getName());

            List<Media> watched = w.getWatched();
            assertEquals(2, watched.size());

            checkMediaWatched("movie1", "movie", 9, "Good", watched.get(0));
            checkMediaWatched("show1", "show", 8,"Ok", watched.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}