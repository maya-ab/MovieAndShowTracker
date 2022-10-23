package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MediaTest  {

    Media movie1;

    @BeforeEach
    public void setUp() {
        movie1 = new Media("Halloween", "Movie");
    }

    @Test
    public void testGiveRating() {

        assertEquals("Halloween", movie1.title);
        assertEquals("Movie", movie1.type);
        try {
            movie1.addRating(8);
        } catch (Exception e) {
            fail("Shouldn't have thrown exception");
        }
        assertEquals(8, movie1.rating);
    }

    @Test
    public void testOutOfRangeRating() {
        try {
            movie1.addRating(11);
            fail("Should have thrown exception");
        } catch (Exception e) {
           //pass, expected
        }

        try {
            movie1.addRating(0);
            fail("Should have thrown exception");
        } catch (Exception e) {
            //pass, expected
        }

    }

    @Test
    public void testGiveNote() {
        movie1.addNote("Good movie");
        assertEquals("Good movie", movie1.note);
    }


}
