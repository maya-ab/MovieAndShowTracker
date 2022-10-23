package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WantToWatchTest {

    WantToWatch w;
    Media show1;
    Media movie1;
    Media movie2;

    @BeforeEach
    public void setUp() {
        w = new WantToWatch("Test");
        show1 = new Media("Lost", "Show");
        movie1 = new Media("Halloween", "Movie");
        movie2 = new Media("Beetlejuice", "Movie");
    }

    @Test
    public void testAddWantToWatch() {
        assertEquals(0, w.wantToWatch.size());
        w.addMediaWantToWatch(show1);
        assertEquals(1, w.wantToWatch.size());

    }

    @Test
    public void testGetTitlesWantToWatch() {
        w.addMediaWantToWatch(show1);
        w.addMediaWantToWatch(movie1);
        w.addMediaWantToWatch(movie2);

        List<String> actualTitles = new ArrayList<String>();
        actualTitles.add("Show: Lost");
        actualTitles.add("Movie: Halloween");
        actualTitles.add("Movie: Beetlejuice");

        assertEquals(actualTitles, w.getTitlesWantToWatch());
    }

    @Test
    public void testGetTitlesWantToWatchEmpty() {
        List<String> actualTitles = new ArrayList<String>();
        assertEquals(actualTitles, w.getTitlesWantToWatch());

    }

    @Test
    public void testMoveToWatchMultipleMedia() {
        Watched watchedList = new Watched("name");

        w.addMediaWantToWatch(show1);
        w.addMediaWantToWatch(movie1);
        w.addMediaWantToWatch(movie2);

        w.moveToWatched("Halloween", watchedList);

        List<String> actualTitlesWantToWatch = new ArrayList<String>();
        actualTitlesWantToWatch.add("Show: Lost");
        actualTitlesWantToWatch.add("Movie: Beetlejuice");


        List<String> actualTitlesWatched = new ArrayList<String>();
        actualTitlesWatched.add("Halloween");

        assertEquals(actualTitlesWantToWatch, w.getTitlesWantToWatch());
        assertEquals(actualTitlesWatched, watchedList.getTitlesWatchedList());
    }

    @Test
    public void testMoveToWatchOneMedia() {
        Watched watchedList = new Watched("name");
        watchedList.addMedia(show1);
        w.addMediaWantToWatch(movie1);
        w.moveToWatched("Halloween", watchedList);

        List<String> actualTitlesWatched = new ArrayList<String>();
        actualTitlesWatched.add("Lost");
        actualTitlesWatched.add("Halloween");
        List<String> actualTitlesWantToWatch = new ArrayList<String>();

        assertEquals(actualTitlesWatched, watchedList.getTitlesWatchedList());
        assertEquals(actualTitlesWantToWatch, w.getTitlesWantToWatch());

    }

    @Test
    public void testMoveToWatchEmpty() {
        Watched watchedList = new Watched("name");
        List<String> actualTitlesWatched = new ArrayList<String>();
        w.moveToWatched("Halloween", watchedList);
        List<String> actualTitlesWantToWatch = new ArrayList<String>();

        assertEquals(actualTitlesWatched, watchedList.getTitlesWatchedList());
        assertEquals(actualTitlesWantToWatch, w.getTitlesWantToWatch());
    }

}
