package no.mattias.recommender;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import no.mattias.recommender.model.MovieRating;
import no.mattias.recommender.model.UserRecommendationModel;

public class ParseResultsTests extends TestCase {

    public ParseResultsTests() {
        super();
    }
    
    public static Test suite()
    {
        return new TestSuite( ParseResultsTests.class );
    }

    public void test_ParseResult_returnsModel() throws JsonParseException, JsonMappingException, IOException
    {
        // Data from a row of ratings. Always helpful to test jackson mappings.
        String resultJson = "{\"userId\":471,\"recommendations\":[{\"movieId\":390,\"rating\":6.1007175},{\"movieId\":5114,\"rating\":5.693139},{\"movieId\":4914,\"rating\":5.6616573},{\"movieId\":1939,\"rating\":5.509304},{\"movieId\":2920,\"rating\":5.466823}]}";

        ObjectMapper mapper = new ObjectMapper();
        UserRecommendationModel recommendationModel = mapper.readValue(resultJson, UserRecommendationModel.class);
        
        assertNotNull(recommendationModel);
        assertEquals(471, recommendationModel.getUserId());
        assertEquals(5, recommendationModel.getRecommendations().size());
    }
}
