package persistence;

import model.Media;
import model.WantToWatch;

import model.Watched;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Modelled after test/persistence/JsonReaderTest/
// Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");

        try {
            WantToWatch wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }

        JsonReader readerWatched = new JsonReader("./data/noSuchFile.json");
        try {
            Watched w = readerWatched.readWatched();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }

    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            WantToWatch wr = reader.read();
            assertEquals("My work room", wr.getName());
            assertEquals(0, wr.numMedias());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

        JsonReader readerWatched = new JsonReader("./data/testReaderEmptyWorkRoomWatched.json");
        try {
            Watched w = readerWatched.readWatched();
            assertEquals("My work room", w.getName());
            assertEquals(0, w.numMedias());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            WantToWatch wr = reader.read();
            assertEquals("My work room", wr.getName());
            List<Media> wantToWatch = wr.getWantToWatch();

            assertEquals(2, wantToWatch.size());

            checkMedia("movie1", "movie", wantToWatch.get(0));
            checkMedia("show1", "show", wantToWatch.get(1));
            
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoomWatched() {
        JsonReader readerWatched = new JsonReader("./data/testReaderGeneralWorkRoomWatched.json");
        try {
            Watched w = readerWatched.readWatched();
            assertEquals("My work room", w.getName());
            List<Media> watched = w.getWatched();

            assertEquals(2, watched.size());

            checkMediaWatched("movie1", "movie", 9, "Good", watched.get(0));
            checkMediaWatched("show1", "show", 8, "Ok", watched.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}