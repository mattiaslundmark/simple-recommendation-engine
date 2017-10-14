package no.mattias.recommender;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import no.mattias.recommender.model.MovieRating;

public class ParseRatingTests extends TestCase {

    public ParseRatingTests() {
        super();
    }
    
    public static Test suite()
    {
        return new TestSuite( ParseRatingTests.class );
    }

    public void test_ParseRating_ReturnsRatingObject()
    {
        // Data from a row of ratings
        String ratingRow = "664,1213,4.0,1343746780";
        MovieRating rating = new MovieRating(ratingRow);
        assertEquals(rating.getUserId(), 664);
        assertEquals(rating.getMovieId(), 1213);
    }
}
