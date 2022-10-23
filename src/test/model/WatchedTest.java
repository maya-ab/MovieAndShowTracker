package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class WatchedTest {
    Watched w;
    Media show1;
    Media movie1;
    Media movie2;

    @BeforeEach
    public void setUp() {
        w = new Watched("name");
        show1 = new Media("Lost", "Show");
        movie1 = new Media("Halloween", "Movie");
        movie2 = new Media("Beetlejuice", "Movie");
    }

    @Test
    public void testAddMedia() {
        assertEquals(0, w.watchedList.size());
        w.addMedia(show1);
        assertEquals(1, w.watchedList.size());
    }

    @Test
    public void testGetTitlesWatchedListMultipleItems() {
        w.addMedia(show1);
        w.addMedia(movie1);
        w.addMedia(movie2);

        List<String> actualTitles = new ArrayList<String>();
        actualTitles.add("Lost");
        actualTitles.add("Halloween");
        actualTitles.add("Beetlejuice");

        assertEquals(actualTitles, w.getTitlesWatchedList());
    }

    @Test
    public void testGetTitlesWatchedListEmpty() {
        List<String> actualTitles = new ArrayList<String>();
        assertEquals(actualTitles, w.getTitlesWatchedList());
    }

    @Test
    public void testGetMediaDetailsMultiple() {
        try {
            show1.addRating(8);
        } catch (Exception e) {
            fail("Shouldn't have thrown exception");
        }
        show1.addNote("Good");
        w.addMedia(show1);

        w.addMedia(movie1);
        w.addMedia(movie2);

        List<String> actualDetails = new ArrayList<String>();
        actualDetails.add("Lost");
        actualDetails.add("Show");
        actualDetails.add("8");
        actualDetails.add("Good");

        assertEquals(actualDetails, w.getMediaDetails("Lost"));
    }

    @Test
    public void testGetMediaDetailsNotInWatched() {
        w.addMedia(show1);
        List<String> actualDetails = new ArrayList<String>();

        assertEquals(actualDetails, w.getMediaDetails("Halloween"));
    }

    @Test
    public void testGetMediaDetailsEmptyWatched() {
        List<String> actualDetails = new ArrayList<String>();
        assertEquals(actualDetails, w.getMediaDetails("Lost"));
    }

    @Test
    public void testAddRatingAndNote() {
        w.addMedia(movie1);
        w.addMedia(movie2);
        w.addMedia(show1);
        w.addRatingAndNote("Lost", 8, "good");

        assertEquals(show1.rating, 8);
        assertEquals(show1.note, "good");
    }

    @Test
    public void testAddRatingAndNoteReplace() {
        try {
            movie1.addRating(8);
        } catch (Exception e) {
            fail("Shouldn't have thrown exception");
        }
        movie1.addNote("good");

        w.addMedia(movie1);
        w.addRatingAndNote("Halloween", 7, "Interesting");

        assertEquals(movie1.rating, 7);
        assertEquals(movie1.note, "Interesting");
    }

    @Test
    public void testAddRatingAndNoteEmpty() {
        w.addRatingAndNote("Halloween", 7, "Interesting");
        assertNotEquals(7,movie1.rating);
        assertNotEquals("Interesting", movie1.note);

    }

    @Test
    public void testAddRatingOutOfRange() {
        w.addMedia(movie1);

        w.addRatingAndNote("Halloween", 11, "Interesting");
    }



}
