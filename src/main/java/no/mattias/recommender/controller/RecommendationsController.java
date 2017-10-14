package no.mattias.recommender.controller;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.mattias.recommender.model.UserRecommendationModel;

@RestController
public class RecommendationsController {

    @Autowired
    ServletContext context;
    
    /**
     * Get recommendations for a specific user
     * @param userId
     * @return
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    @RequestMapping("/recommend/{userId}")
    public ResponseEntity recommend(
            @PathVariable("userId") int userId) throws JsonParseException, JsonMappingException, IOException {
        
        Dataset<Row> recommendForAllUsers = (Dataset<Row>) context.getAttribute("model");
        
        if(recommendForAllUsers == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
        Dataset<Row> filter = recommendForAllUsers.filter(recommendForAllUsers.col("userId").equalTo(userId));
        Dataset<String> json = filter.toJSON();
        String result = json.first();
        
        ObjectMapper mapper = new ObjectMapper();
        
        UserRecommendationModel resultModel = mapper.readValue(result, UserRecommendationModel.class);

        return ResponseEntity.ok(resultModel);
    }
}
